//package kp.cm.common.file;
//
//import java.io.File;
//import java.io.IOException;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class ClearUploadFolder extends javax.servlet.http.HttpServlet implements
//		javax.servlet.Servlet {
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		//String contextRealPath = request.getSession().getServletContext().getRealPath("/");
//		//String savePath = contextRealPath + "xp_upfolder";
//		String savePath = "D:/framework/workspace/nbosspoc/WebContent/xp_upfolder";
//		File upFolder = new File(savePath);
//
//		File uploadedFile = null;
//		String[] files = upFolder.list();
////System.out.println("files.length=["+files.length+"]");
//		if (files != null && files.length > 0) {
//			for (int i = 0; i < files.length; i++) {
//				uploadedFile = new File(savePath + "/" + files[i]);
//				if(uploadedFile.exists()) {
//					uploadedFile.delete();
//				}
//			}
//
//			request.setAttribute("msg", "삭제 완료!!");
//		} else {
//			request.setAttribute("msg", "삭제할 파일이 존재하지 않습니다.!!");
//		}
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/XpMyTemp_jsp/xp_up_download_main.jsp");
//		rd.forward(request, response);
//	}
//}
