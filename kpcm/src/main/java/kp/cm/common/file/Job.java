package kp.cm.common.file;

import java.util.concurrent.Future;

public abstract class Job {
	private Future<?> future = null;

	public void setJobThread(Future<?> future) {
		this.future = future;
	}

	public Future<?> getJobThread() {
		return this.future;
	}
}
