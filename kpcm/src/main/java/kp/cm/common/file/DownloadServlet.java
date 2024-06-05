//package kp.cm.common.file;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Locale;
//
//import org.apache.logging.log4j.Logger;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletOutputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import kp.cm.common.util.ResourceUtil;
//import kp.cm.common.util.StringUtil;
//
//public class DownloadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
//
//	private ResourceUtil resourceUtil = new ResourceUtil();
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		service(request, response);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		service(request, response);
//	}
//
//	@Override
//	public void service(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
//
//		String sUpmuUnit = request.getParameter("UPMU_UNIT");
//		String sUserId = request.getParameter("USER_ID");
//		resourceUtil.setBasename(resourceUtil.getServerBaseName(request));
//		String savePath = resourceUtil.getMessage("SAVE_PATH", null, Locale.getDefault());
//		//TODO 실제 저장될 경로는 업무아이디 + 로그인 아이디
//		if(sUserId.equals("''")) {//로그인아이디가 ''일 경우에는 업무아이디 밑에 파일이 있다.
//			savePath = savePath + File.separator + sUpmuUnit;
//		}else{
//			savePath = savePath + File.separator + sUpmuUnit + File.separator + sUserId;
//		}
//		String name = request.getParameter("file");
//		String filename = new String(name.getBytes("iso8859-1"), "UTF-8");
//		String truth_file_nm = java.net.URLDecoder.decode(StringUtil.checkNull(request.getParameter("truth")));
//		// 자바 I/O 를 이용하여 다운로드해 준다.
//		byte[] buffer = new byte[1024];
//		ServletOutputStream out = null;
//		BufferedInputStream in = null;
//		try {
//			File file = new File(savePath + File.separator + filename);
////			System.out.println(file.exists());
//			if(file.exists()) {
//				out = res.getOutputStream();
//				res.setContentType("utf-8");
//				res.setContentType("application/octet;charset=utf-8");
//				res.setHeader("Accept-Ranges", "bytes");
//				res.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(truth_file_nm,"UTF8"));
//				long len = file.length();
//				res.setContentLength((int)len);
//				in = new BufferedInputStream(new FileInputStream(file));
//				int n = 0;
//				while ((n = in.read(buffer, 0, 1024)) != -1) {
//					out.write(buffer, 0, n);
//				}// while
//			} else {
//
//				//TODO res.sendRedirect("unknownfile"); //파일이 없으면 없는 페이지로 리턴
//			}
//		} catch (Exception e) {
//			//out.println("다운로드 예외 발생 : " + e);
//		} finally {
//			if (in != null) {
//                try {
//					in.close();
//				} catch (Exception e) {}
//            }
//			if (out != null) {
//                try {
//					out.close();
//				} catch (Exception e) {}
//            }
//		}// finally
//
//	}// service
//
//}
