/**
 * properties 파일의 메세지를 읽어옴.
 * 프로퍼티를 읽을 때 한글이 깨지기 때문에 ResourceBundleMessageSource 를 상속받아 charset 을 UTF-8 로 변경하여 반환함.
 * 프로퍼티 파일 자체를 다국어 지원 가능하게 관리하는 방법이 있지만, 별도 관리방법이 까다롭기 때문에 상속으로 구현.
 * 스프링에서 지원하는 ReloadableResourceBundleMessageSource 클래스를 사용하면
 * 다국어 를 기본으로 지원하고,
 * properties 파일이 변경돼었을 때, 서버가 자동으로 재시작되어 반영되지만,
 * 운영중인 환경에서 서버가 자동으로 재시작되면 난감하기 때문에 적용에서 제외.
 *
 * Copyright(c) 2010 UCUBE. All rights reserved.
 *
 * @since 2010-09-14
 * @author Jung Sung Hyun
 */
package kp.cm.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import jakarta.servlet.http.HttpServletRequest;

public class ResourceUtil extends ResourceBundleMessageSource {

	protected String charset = "UTF-8";

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
    protected String getMessageInternal(String code, Object[] args, Locale locale) {
		String originalMessage = super.getMessageInternal(code, args, locale);
		if (originalMessage == null) {
            return null;
        }
		try {
			return new String(originalMessage.getBytes("8859_1"), charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getServerBaseName(HttpServletRequest request){

		String strBaseName = null;
		String strServer = request.getServerName();
		if(strServer.indexOf("www.aaaa.kr") > -1){ //운영
			strBaseName = "/properties/path_real";
		}
		else if(strServer.indexOf("edupot.iptime.org") > -1 || strServer.indexOf("192.168.0.2") > -1){ //개발
			strBaseName = "/properties/path_dev";
		}
		else { //로컬
			strBaseName = "/properties/path_local";
		}
		return strBaseName;
	}
}
