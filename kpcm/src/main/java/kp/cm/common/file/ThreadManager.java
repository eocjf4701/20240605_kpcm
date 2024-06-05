package kp.cm.common.file;

import java.util.Hashtable;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service("ThreadManager")

@Scope("prototype")
public class ThreadManager {
	@Autowired
	ThreadPoolTaskExecutor executor;

	@Autowired
	Hashtable<String, Job> jobManager;

	public static Object threadSynchronized = new Object();
	private static final Log log = LogFactory.getLog(ThreadManager.class);

	public void clearThread(){
		Future<?> future = null;

		for (String key : jobManager.keySet()) {
			future = jobManager.get(key).getJobThread();

			if(future != null && future.isDone()){
				future = null;
				log.debug(key + " thread 제거");
				jobManager.remove(key);
			}
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void interruptThread(String threadName){
		synchronized (threadSynchronized) {
			Future<?> future = (Future<?>)jobManager.get(threadName);
			log.debug(threadName + " 중지");
			future.cancel(true);
		}
	}

	public void start(String key, Runnable run, Job job) throws NotRunableException{
		Future<?> future = executor.submit(run);

		synchronized (threadSynchronized) {
//			Job job = null;
			if(job == null){
				if(jobManager.containsKey(key)){
					job = jobManager.get(key);
				}else{
					throw new NotRunableException();
				}
			}else{
				if(!jobManager.containsKey(key)){
					jobManager.put(key, job);
				}
			}

			job.setJobThread(future);
		}

//		try {
//	        future.get();
//	    } catch (ExecutionException e) {
//	        // Access the exception thrown by the different thread.
//	        e.getCause().printStackTrace();
//	    } catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public class NotRunableException extends Exception{

	}
}
