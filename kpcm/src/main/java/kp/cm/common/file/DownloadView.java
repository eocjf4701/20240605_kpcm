package kp.cm.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DownloadView extends AbstractView {

	public void Download() {
		setContentType("application/download; utf-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		File file = (File) model.get("downloadFile");
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());

		String userAgent = request.getHeader("User-Agent");

		String originalFileName = (String)model.get("fileName");

		if (userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5 이하
			response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "\\ ") + ";");
		} else if (userAgent.indexOf("MSIE") > -1) { // MS IE (보통은 6.x 이상 가정)
			response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "\\ ") + ";");
		} else if (userAgent.indexOf("Trident") > -1) { // MS IE 11
			response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "\\ ") + ";");
		} else { // 모질라나 오페라
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(originalFileName.getBytes("euc-kr"), "latin1").replaceAll("\\+", "\\ ") + ";");
		}

		response.setHeader("Content-Transfer-Encoding", "binary");

		OutputStream out = response.getOutputStream();

		FileInputStream fis = null;

		try {

			fis = new FileInputStream(file);

			FileCopyUtils.copy(fis, out);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (fis != null) {

				try {
					fis.close();
				} catch (Exception e) {
				}
			}

		}// try end;

		out.flush();
	}

}
