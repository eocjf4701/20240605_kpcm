//package kp.cm.common.file;
//
//import java.io.ByteArrayInputStream;
//import java.io.ObjectInputStream;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.Resource;
//import jakarta.servlet.ServletContext;
//import wemsin.common.service.ComService;
//
//@Component
//public class FileDownloadScheduler {
//	private static boolean isFirstLoad = true;
//
//	private Logger logger = Logger.getLogger(this.getClass());
//
//	@Resource(name = "comService")
//	private ComService comService;
//
//	@Resource(name="ThreadManager")
//	private ThreadManager threadManager;
//
//	@Autowired
//    private ServletContext servletContext;
//
//	@Autowired
//	Hashtable<String, Job> jobManager;
//
//	@Resource(name="FileDownload")
//	private FileDownload fileDownload;
//
//	@Resource(name = "comService")
//	private DownloadService comDownloadService;
//
//	@Resource(name = "weaService")
//	private DownloadService weaDownloadService;
//
//	@Resource(name = "wedService")
//	private DownloadService wedDownloadService;
//
//	@Resource(name = "webService")
//	private DownloadService webDownloadService;
//
//	@Resource(name = "wecService")
//	private DownloadService wecDownloadService;
//
//	@Resource(name = "wegService")
//	private DownloadService wegDownloadService;
//
//	@Resource(name = "wefService")
//	private DownloadService wefDownloadService;
//
//	@Resource(name = "weeService")
//	private DownloadService weeDownloadService;
//
//	/**
//	 * 파일 다운로드 스케줄러
//	 */
//	@Scheduled(cron = "*/5 * * * * *")
//	public void searchFileDownloadJob(){
//		List<Map<String, Object>> downloadData = null;
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//
//		try {
//			if(isFirstLoad){
//				paramMap.put("mkFileSt", "1");
//				paramMap.put("mkFileStSet", "0");
//				comService.download130ListStatusChange(paramMap);
//				comService.download131ListStatusChange(paramMap);
//				downloadData = comService.selectDownload();
//				isFirstLoad = false;
//			}else{
//				downloadData = comService.selectDownload();
//			}
//
//			if(downloadData.size() > 0){
//				String userId = downloadData.get(0).get("mbrId").toString();
//
//				// '01'이 기본적으로 들어오지 않음
//				if(paramMap.get("xlsInspStadCd") instanceof String[]){
//					paramMap.put("xlsInspStadCd", new String[] { "01", "02", "99"});
//				}else{
//					paramMap.put("xlsInspStadCd", new String[] { "01", "02", "99"});
//				}
//
//				paramMap.put("userID", userId);
//				paramMap.put("fileBizDiv", downloadData.get(0).get("fileBizDiv").toString());
//				paramMap.put("fileSeq", downloadData.get(0).get("fileSeq").toString());
//				paramMap.put("fileNm", downloadData.get(0).get("fileNm").toString());
//
//				paramMap.put("mkFileSt", "0");
//				paramMap.put("mkFileStSet", "1");
//
//				threadManager.clearThread();
//
//				if("0".equals(downloadData.get(0).get("flag").toString())){
//					comService.download130ListStatusChange(paramMap);
//					threadManager.start(userId, new FileManageDownload(paramMap, servletContext, jobManager, fileDownload, comDownloadService, comService), new DownloadJob());
//				}else{
//					String fileBizDiv = downloadData.get(0).get("fileBizDiv").toString();
//					String optionData = downloadData.get(0).get("optionData").toString();
//					DownloadService downloadService = null;
//
//					if ("01".equals(fileBizDiv)) {
//
//					} else if ("02".equals(fileBizDiv)) {
//						downloadService = weaDownloadService;
//					} else if ("03".equals(fileBizDiv)) {
//						downloadService = webDownloadService;
//					} else if ("04".equals(fileBizDiv)) {
//						downloadService = webDownloadService;
//					} else if ("05".equals(fileBizDiv)) {
//						downloadService = webDownloadService;
//					} else if ("06".equals(fileBizDiv)) {
//						downloadService = webDownloadService;
//					} else if ("07".equals(fileBizDiv)) {
//						downloadService = wecDownloadService;
//					} else if ("08".equals(fileBizDiv)) {
//						downloadService = wedDownloadService;
//					} else if ("09".equals(fileBizDiv)) {
//						downloadService = weeDownloadService;
//					} else if ("10".equals(fileBizDiv)) {
//						downloadService = wefDownloadService;
//					} else if ("11".equals(fileBizDiv)) {
//						downloadService = wegDownloadService;
//					} else if ("12".equals(fileBizDiv)) {
//
//					} else if ("13".equals(fileBizDiv)) {
//						downloadService = weaDownloadService;
//					} else if ("14".equals(fileBizDiv)) {
//						downloadService = weaDownloadService;
//					} else if ("15".equals(fileBizDiv)) {
//						downloadService = webDownloadService;
//					} else if ("16".equals(fileBizDiv)) {
//						downloadService = webDownloadService;
//					} else if ("17".equals(fileBizDiv)) {
//						downloadService = webDownloadService;
//					}else if("18".equals(fileBizDiv)){
//						downloadService = weaDownloadService;
//					}
//
//					byte[] binary = Base64.decodeBase64(optionData);
//					ByteArrayInputStream bais = new ByteArrayInputStream(binary);
//					ObjectInputStream ois = new ObjectInputStream(bais);
//					Map<String, Object> optionDataMap = (Map<String, Object>) ois.readObject();
//
//					paramMap.put("accpClsf", optionDataMap.get("accpClsf"));
//					paramMap.put("accpMbrId", optionDataMap.get("accpMbrId"));
//					paramMap.put("paymentYYYY", optionDataMap.get("paymentYYYY"));
//					paramMap.put("fileBizDiv", downloadData.get(0).get("fileBizDiv"));
//					paramMap.put("sido2select", downloadData.get(0).get("fileSeq"));
//
//					comService.download131ListStatusChange(paramMap);
//					threadManager.start(userId, new IndividualManageDownload(paramMap,  servletContext, jobManager, fileDownload, downloadService, comService), new DownloadJob());
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.error ("something wrong", e);
//		}finally{
//			paramMap = null;
//		}
//	}
//
//
//	/**
//	 * 오랜된 파일 삭제를 위한 스케줄러
//	 * 파일 삭제 기간은 15일로 정의됨
//	 */
//	@Scheduled(cron = "*/60 * * * * *")
//	public void deleteOldFileCheck(){
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		List<Map<String, Object>> deleteFileList = null;
//		try {
//			deleteFileList = comService.checkOldFile();
//
//			for (int a = 0; a < deleteFileList.size(); a++) {
//				paramMap.put("fileBizDiv", deleteFileList.get(a).get("fileBizDiv").toString());
//				paramMap.put("mbrId", deleteFileList.get(a).get("mbrId").toString());
//				paramMap.put("fileSeq", deleteFileList.get(a).get("fileSeq").toString());
//
//				comService.deleteDownloadFile(paramMap);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
