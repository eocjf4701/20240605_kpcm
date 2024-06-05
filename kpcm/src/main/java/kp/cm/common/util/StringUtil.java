package kp.cm.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

//import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;

public class StringUtil {
    public static final String EMPTY = "";
    private static final String PADDING[];

    static {
        PADDING = new String[65535];
        PADDING[32] = "                                                                ";
    }

    public static synchronized String java2Html(Object o) {
        if (o == null)
            return "";
        String value = null;

        try {
            Class<? extends Object> c = o.getClass();
            if (c.isArray()) {
                int length = Array.getLength(o);
                if (length == 0) {
                    value = "";
                } else {
                    Object item = Array.get(o, 0);
                    if (item == null) {
                        value = "";
                    } else {
                        value = item.toString();
                    }
                }
            } else {
                value = o.toString();
            }
        } catch (Exception e) {
            value = "";
        }

        StringBuffer buf = new StringBuffer();
        char[] c = value.toCharArray();
        int len = c.length;
        for (int i = 0; i < len; i++) {
            if (c[i] == '&')
                buf.append("&amp;");
            else if (c[i] == '<')
                buf.append("&lt;");
            else if (c[i] == '>')
                buf.append("&gt;");
            else if (c[i] == '"')
                buf.append("&quot;");
            else if (c[i] == '\'')
                buf.append("#39;");
            else
                buf.append(c[i]);
        }
        return buf.toString();
    }

    /**
     * 특수문자를 웹 브라우저에서 정상적으로 보이기 위해 특수문자를 처리('<' -> & lT)하는 기능이다
     * 
     * @param srcString - '<'
     * @return 변환문자열('<' -> "<"
     * @exception MyException
     * @see
     */
    public static String getSpclStrCnvr(String srcString) {

        String rtnStr = null;

        try {
            StringBuffer strTxt = new StringBuffer("");

            char chrBuff;
            int len = srcString.length();

            for (int i = 0; i < len; i++) {
                chrBuff = (char) srcString.charAt(i);

                switch (chrBuff) {
                case '<':
                    strTxt.append("<");
                    break;
                case '>':
                    strTxt.append(">");
                    break;
                case '&':
                    strTxt.append("&");
                    break;
                default:
                    strTxt.append(chrBuff);
                }
            }

            rtnStr = strTxt.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rtnStr;
    }

    public static String checkNull(Object o) {
        String str = o != null ? ("null".equals(o.toString().trim()) ? "" : o.toString().trim()) : "";
        return str;
    }

    public static String checkNull(Object o, String defaultStr) {
        String str = checkNull(o);
        if ("".equals(str)) {
            str = defaultStr;
        }
        return str;
    }

    public static String subString(String str, int beginIndex, int endIndex) {
        byte[] orgByteStr = str.getBytes();
        byte[] newByteStr = null;
        int size = orgByteStr.length;

        if (beginIndex > endIndex) {
            // System.out.println("[Error] com.tmax.common.util.StringUtil.substring :
            // beginIndex > endIndex");
            return str;
        } else if (beginIndex > size) {
            // System.out.println("[Error] com.tmax.common.util.StringUtil.substring :
            // beginIndex > size");
            return str;
        } else if (beginIndex < 0) {
            // System.out.println("[Error] com.tmax.common.util.StringUtil.substring :
            // beginIndex < 0");
            return str;
        } else if (endIndex > size) {
            // System.out.println("[Error] com.tmax.common.util.StringUtil.substring :
            // endIndex > size");
            return str;
        } else if (endIndex < 0) {
            // System.out.println("[Error] com.tmax.common.util.StringUtil.substring :
            // endIndex < 0");
            return str;
        } else {
            newByteStr = new byte[endIndex - beginIndex];
            int tmpIndex = 0;
            for (int i = 0; i < size; i++) {
                if (i >= beginIndex && i < endIndex) {
                    newByteStr[tmpIndex] = orgByteStr[i];
                    tmpIndex++;
                }
                if (tmpIndex == newByteStr.length)
                    break;
            }
            return new String(newByteStr);
        }
    }

    public static String removeComma(String number) {
        return replaceAll(number, ",", "");
    }

    public static String replaceAll(String str, String oldStr, String newStr) {
        String line = "";
        int index = 0;
        while ((index = str.indexOf(oldStr, index)) >= 0) {
            line = str.substring(0, index) + newStr + str.substring(index + oldStr.length());
            str = line;
            index += newStr.length();
        }
        return str;
    }

    /*************************** 정성현 이하는 추가 ********************/

    /**
     * 
     * 문자를 UniCode로 변환 한다.<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2008. 9. 18.
     * @param String 변환할 문자
     * @return UniCode
     */
    public static String uniCodeConvertor(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            sb.append("\\");
            char c = s.charAt(i);
            sb.append(leftPad(String.valueOf(Integer.toHexString(c)), 4, "0"));
        }
        return sb.toString();
    }

    /**
     * 
     * KSC5601 을 8859_1 문자셋으로 변경한다.<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param ko KSC5601 문자
     * @return 8859_1 문자
     */
    public static String toEN(String ko) {
        String new_str = null;
        try {
            if (ko != null) {
                new_str = new String(ko.getBytes("KSC5601"), "8859_1");
            } else {
                new_str = "";
            }
        } catch (UnsupportedEncodingException e) {
        }
        return new_str;
    }

    /**
     * 
     * 8859_1 을 KSC5601 문자셋으로 변경한다.<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param en 8859_1 문자
     * @return KSC5601 문자
     */
    public static String toKSC(String en) {
        String new_str = null;
        try {
            if (en != null) {
                new_str = new String(en.getBytes("8859_1"), "KSC5601");
            } else {
                new_str = "";
            }
        } catch (UnsupportedEncodingException e) {
        }
        return new_str;
    }

    public static boolean isKorean(char c) {
        if (44032 <= c && c <= 55203)
            return true;
        return false;
    }

    public static String absToKSC(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        char[] chArr = toKSC(str).toCharArray();
        boolean isKorean = false;
        for (int i = 0; i < chArr.length; i++) {
            isKorean = isKorean(chArr[i]);
            if (isKorean)
                break;
        }
        if (isKorean) {
            str = toKSC(str);
        }

        return str;
    }

    /**
     * URLEncode<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 11. 22.
     * @param value
     * @param condition
     * @param isDefault
     * @return
     */
    public static String URLEncode(String ksc) {
        if (ksc != null) {
            try {
                return URLEncoder.encode(ksc, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        return "";
    }

    /**
     * 
     * URLDecode<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 11. 22.
     * @param value
     * @param condition
     * @param isDefault
     * @return
     */
    public static String URLDecode(String ksc) {
        if (ksc != null) {
            try {
                return URLDecoder.decode(ksc, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        return "";
    }

    /**
     * URL에 공백이 있는경우 %20으로 리턴해준다.<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2008. 11. 17.
     * @param value
     * @param condition
     * @param isDefault
     * @return
     */

    /*
     * public static String parseBlankString(String param){ return
     * java.net.URLEncoder.encode(param).replace("+","%20").toString(); }
     */

    /**
     * 
     * 문자를 int 형으로 변환<br>
     * Exception 발생시 default_num 을 반환
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param str
     * @param default_num 에러 발생시 반환할 기본 값
     * @return
     */
    public static int parseInt(String str, int default_num) {
        int parseInt = 0;
        try {
            parseInt = Integer.parseInt(str);
        } catch (Exception nf) {
            parseInt = default_num;
        }
        return parseInt;
    }

    /**
     * 
     * 문자를 int 형으로 변환<br>
     * Exception 발생시 0 을 반환
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param str
     * @return
     */
    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    /**
     * 
     * 문자를 float 형으로 변환<br>
     * Exception 발생시 default_num 을 반환
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param str
     * @param default_num 에러 발생시 반환할 기본 값
     * @return
     */
    public static float parseFloat(String str, float default_num) {
        float parseFloat = 0.0f;
        try {
            parseFloat = Float.parseFloat(str);
        } catch (Exception nf) {
            parseFloat = default_num;
        }
        return parseFloat;
    }

    /**
     * 
     * 문자를 float 형으로 변환<br>
     * Exception 발생시 0.0f 을 반환
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param str
     * @return
     */
    public static float parseFloat(String str) {
        return parseFloat(str, 0.0f);
    }

    /**
     * 
     * 문자를 long 형으로 변환<br>
     * Exception 발생시 0.0f 을 반환
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param str
     * @param default_num 에러 발생시 반환할 기본 값
     * @return
     */
    public static long parseLong(String str, long default_num) {
        long parseLong = 0L;
        try {
            parseLong = Long.parseLong(str);
        } catch (Exception nf) {
            parseLong = default_num;
        }
        return parseLong;
    }

    /**
     * 
     * 문자를 long 형으로 변환<br>
     * Exception 발생시 0.0f 을 반환
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param str
     * @return
     */
    public static long parseLong(String str) {
        return parseLong(str, 0L);
    }

    /**
     * 문자를 double 형으로 변환<br>
     * Exception 발생시 0 을 반환
     * 
     * @param str
     * @param default_num 에러 발생시 반환할 기본 값
     * @return
     */
    public static double parseDouble(String str, double default_num) {
        double parseDouble = 0;
        try {
            parseDouble = Double.parseDouble(str);
        } catch (Exception nf) {
            parseDouble = default_num;
        }
        return parseDouble;
    }

    /**
     * 문자를 double 형으로 변환<br>
     * Exception 발생시 0 을 반환
     * 
     * @param str
     * @return
     */
    public static double parseDouble(String str) {
        return parseDouble(str, 0);
    }

    /**
     * 
     * 가격등의 값을 3자리마다 comma(,)로 구분하여 반환<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param num
     * @return
     */
    public static String parseDecimal(double num) {
        return parseDecimal(num, "###,###,###,###.##");
    }

    public static String parseDecimal(String num) {
        if (num == null || "".equals(num)) {
            return "0";
        } else {
            return parseDecimal(Double.parseDouble(num));
        }
    }

    /**
     * 
     * 가격등의 값을 주어진 포맷에 맞춰 리턴<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param num
     * @param foramt
     * @return
     */
    public static String parseDecimal(double num, String format) {
        String type = "###,###,###,###.##";
        String result = "";
        if (format != null)
            type = format;
        try {
            DecimalFormat df = new DecimalFormat(type);
            result = df.format(num);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 
     * 구분자로 결합되어있는 문자열을 구분자를 기준으로 분리하고,<br>
     * 분리된 내용을 문자열배열로 재 정의한다.
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param src    원본 문자열
     * @param parser 구분자 (,)
     * @return
     */
    public static String[] getItemArray(String src, char parser) {

        String[] retVal = null;
        if (src.length() == 0)
            return null;

        int nitem = 1;

        for (int i = 0; i < src.length(); i++)
            if (src.charAt(i) == parser)
                nitem++;

        retVal = new String[nitem];

        int ep = 0;
        int sp = 0;

        for (int i = 0; i < nitem; i++) {
            ep = src.indexOf(parser, sp);
            if (ep == -1)
                ep = src.length();
            retVal[i] = new String(src.substring(sp, ep));
            sp = ep + 1;
        }

        return retVal;
    }

    /**
     *
     * text를 format에 맞추어 출력한다. <br>
     * getFormatedText("0193372412","???-???-????") --->> 019-337-2412로 출력
     *
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param text   원본 문자열
     * @param format 변경할 포맷
     * @return
     */
    public static String getFormatedText(String text, String format) {
        String rtn;
        int start, i, j, len;
        int tCount, fCount;

        tCount = text.length();
        fCount = format.length();

        rtn = "";

        if (text.equals(""))
            return rtn;
        if (text.equals("&nbsp;"))
            return "&nbsp;";
        // text가 01252412 이고 format 이 ????-???? 이면 0125-2412로 출력
        // text에서 -를 제거한다.
        for (i = 0; i < tCount; ++i) {
            if (!text.substring(i, i + 1).equals("-"))
                rtn = rtn + text.substring(i, i + 1);
        }

        text = rtn;
        tCount = text.length();

        // 포멧에서 ?의 count
        len = 0;
        for (j = 0; j < fCount; ++j) {
            if (format.substring(j, j + 1).equals("?"))
                ++len;
        }
        // text의 길이가 len보다 작으면 앞에 0를 붙인다.
        if (tCount < len) {
            for (i = 0; i < (len - tCount); ++i) {
                text = '0' + text;
            }
            tCount = len;
        }

        rtn = "";
        start = 0;
        for (i = 0; i < tCount; ++i) {
            for (j = start; j < fCount; ++j) {
                if (format.substring(j, j + 1).equals("?")) {
                    rtn = rtn + text.substring(i, i + 1);
                    start = start + 1;
                    break;
                } else {
                    rtn = rtn + format.substring(j, j + 1);
                    start = start + 1;
                }
            }
        }
        return rtn + format.substring(start);
    }

    /**
     * 
     * 입력된 숫자를 format 에 맞춰 출력한다.<br>
     * 예) getFormatedInt (123, "??-??") <br>
     * outprint : 01-23
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 19.
     * 
     * @param num
     * @param format
     * @return
     */
    public static String getFormatedInt(int num, String format) {
        return getFormatedText(Integer.toString(num), format);
    }

    /**
     * 
     * 입력된 Object 를 문자열로 변환한다.<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 27.
     * 
     * @param obj
     * @param defValue
     * @return
     */
    public static String toString(Object obj, String defValue) {
        if (obj == null)
            return defValue;

        String str = obj.toString();
        if (str == null || "".equals(str))
            return defValue;

        return str;
    }

    /**
     * 
     * 입력된 Object 를 문자열로 변환한다.<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 27.
     * 
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        return toString(obj, "");
    }

    /**
     * 문자열의 값이 null 이거나 "" 이거나 "null"이면 default 값을 리턴하는 메소드<br>
     *
     * @author : jung sung hyun
     * @e-mail : sim11@mirenet.com
     */
    public static String getString(String line, String def) {
        if (line == null || line.equals("") || line.equals("null"))
            return def;
        return line;
    }

    /**
     * 
     * 
     * 입력된 스트링을 지정된 길이만큼 Byte단위로 남기고 나머지를 잘라낸다.!<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @createdate 2002. 6. 9.
     * @modifydate 2002. 6. 9.
     * 
     * @param str 잘라내고자 하는 원본 문자열
     * @param sz  자르고 남을 문자열의 byte단위 길이.
     * @return 원본 문자열에서 자르고 남은 sz만큼의 문자열
     * @throws UnsupportedEncodingException
     */
    public static String getByteCut(String str, int sz) throws UnsupportedEncodingException {
        str = getString(str, "");

        if (str.equals("") || str.getBytes().length <= sz) {
            return str;
        }

        String a = str;
        int i = 0;
        String imsi = "";
        String rlt = "";
        imsi = a.substring(0, 1);
        while (i < sz) {
            byte[] ar = imsi.getBytes();

            i += ar.length;

            rlt += imsi;
            a = a.substring(1);
            if (a.length() == 1) {
                imsi = a;
            } else if (a.length() > 1) {
                imsi = a.substring(0, 1);
            }
        }

        return rlt + "...";
    }

    /**
     * 
     * 특정 문자열을 다른 문자열로 대체하는 메소드<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 6. 15.
     * 
     * @param line
     * @param oldString
     * @param newString
     * @return
     */
    public static String replace(String line, String oldString, String newString) {
        int index = 0;
        while ((index = line.indexOf(oldString, index)) >= 0) {
            line = line.substring(0, index) + newString + line.substring(index + oldString.length());
            index += newString.length();
        }
        return line;
    }

    /**
     * 
     * 디버깅시 Servlet 에서는 PrintWriter 를 넣어서 쉽게 디버깅을 할 수 있었지만 <br>
     * JSP 에서 처럼 PrintWriter가 없을때 디버깅을 쉽게 하기 위하여 메세지를 문자열로 만들어 리턴하게 하였음.<br>
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 20.
     * 
     * @param e
     * @return
     */
    public static String getStackTrace(Throwable e) {
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        java.io.PrintWriter writer = new java.io.PrintWriter(bos);
        e.printStackTrace(writer);
        writer.flush();
        return bos.toString();
    }

    /**
     * @author
     * @version 1.0
     * @modifydate 2002. 5. 20.
     * 
     * @param e
     * @return
     */
    public static boolean contains(String str, char searchChar) {
        if (str == null || str.length() == 0)
            return false;
        else
            return str.indexOf(searchChar) >= 0;
    }

    /**
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2002. 5. 20.
     * 
     * @param e
     * @return
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null)
            return false;
        else
            return str.indexOf(searchStr) >= 0;
    }

    /**
     * @author cho
     * @param eua130ExaminNo
     * @return
     */
    public static String nullToString(String arg) {
        try {
            return arg;
        } catch (NullPointerException e) {
            return "";
        }

    }

    public static String emptyToNull(String arg) {
        if ("".equals(arg)) {
            return null;
        } else {
            return arg;
        }
    }

    public static String escape(String arg) {
        if (arg.indexOf("\"") > -1) {
            arg = replaceAll(arg, "\"", "\\\"");
        }
        if (arg.indexOf("'") > -1) {
            arg = replaceAll(arg, "\"", "\\\'");
        }
        return arg;
    }

    public static String upEscape(String arg) {
        if (arg.indexOf("\"") > -1) {
            arg = replaceAll(arg, "\\\"", "\"");
        }
        if (arg.indexOf("'") > -1) {
            arg = replaceAll(arg, "\\\'", "\"");
        }
        return arg;
    }

    private static String padding(int repeat, char padChar) {
        String pad = PADDING[padChar];
        if (pad == null)
            pad = String.valueOf(padChar);
        for (; pad.length() < repeat; pad = pad.concat(pad))
            ;
        PADDING[padChar] = pad;
        return pad.substring(0, repeat);
    }

    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    public static String rightPad(String str, int size, char padChar) {
        if (str == null)
            return null;
        int pads = size - str.length();
        if (pads <= 0)
            return str;
        if (pads > 8192)
            return rightPad(str, size, String.valueOf(padChar));
        else
            return str.concat(padding(pads, padChar));
    }

    public static String rightPad(String str, int size, String padStr) {
        if (str == null)
            return null;
        if (padStr == null || padStr.length() == 0)
            padStr = " ";
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0)
            return str;
        if (padLen == 1 && pads <= 8192)
            return rightPad(str, size, padStr.charAt(0));
        if (pads == padLen)
            return str.concat(padStr);
        if (pads < padLen)
            return str.concat(padStr.substring(0, pads));
        char padding[] = new char[pads];
        char padChars[] = padStr.toCharArray();
        for (int i = 0; i < pads; i++)
            padding[i] = padChars[i % padLen];

        return str.concat(new String(padding));
    }

    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    public static String leftPad(String str, int size, char padChar) {
        if (str == null)
            return null;
        int pads = size - str.length();
        if (pads <= 0)
            return str;
        if (pads > 8192)
            return leftPad(str, size, String.valueOf(padChar));
        else
            return padding(pads, padChar).concat(str);
    }

    public static String leftPad(String str, int size, String padStr) {
        if (str == null)
            return null;
        if (padStr == null || padStr.length() == 0)
            padStr = " ";
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0)
            return str;
        if (padLen == 1 && pads <= 8192)
            return leftPad(str, size, padStr.charAt(0));
        if (pads == padLen)
            return padStr.concat(str);
        if (pads < padLen)
            return padStr.substring(0, pads).concat(str);
        char padding[] = new char[pads];
        char padChars[] = padStr.toCharArray();
        for (int i = 0; i < pads; i++)
            padding[i] = padChars[i % padLen];

        return (new String(padding)).concat(str);
    }

    /**
     * 배열을 받아 연결될 문자열로 연결한다 이때 각 엘레먼트 사이에 구분문자열을 추가한다.
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2008. 3. 19.
     * 
     * @param e
     * @return
     */
    public static String StringJoin(Object aStr[], String padStr) {
        StringBuffer strBuff = new StringBuffer();

        int i = aStr.length;
        if (i > 0) {
            strBuff.append(aStr[0]);
        }
        for (int j = 1; j < i; j++) {
            strBuff.append(padStr);
            strBuff.append(aStr[j].toString());
        }
        return strBuff.toString();
    }

    /**
     * 이차원 배열을 받아 연결될 문자열로 연결한다 이때 각 엘레먼트 사이에 구분문자열을 추가한다.
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2008. 3. 19.
     * @param e
     * @return
     */
    public static String[] StringJoin(Object aStr[][], String padStr) {
        int aStrLen = aStr.length;// 배열의 총갯수
        int aStrLenOne = aStr[0].length;// 배열의 항목 갯수
        String[] str = new String[aStrLenOne];

        for (int k = 0; k < aStrLenOne; k++) {// 첫번째 배열 (항목갯수 만큼)
            StringBuffer strBuff = new StringBuffer();
            strBuff.append(aStr[0][k].toString());
            for (int j = 1; j < aStrLen; j++) {// 두번째 배열 (총 배열갯수)
                strBuff.append(padStr);
                strBuff.append(aStr[j][k].toString());
            }
            str[k] = strBuff.toString();
        }

        return str;
    }

    /**
     * 이차원 배열을 받아 연결될 문자열로 연결한다 이때 각 엘레먼트 사이에 구분문자열을 추가한다.
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2008. 3. 19.
     * @param e
     * @return
     */
    public static List<Map<String, Object>> StringJoin(List<Map<String, Object>> list, String padStr) {

        String keyNmTmp = "";
        String keyNmJon = "";
        String keyValJon1 = "";
        String keyValJon2 = "";
        List<Map<String, Object>> setList = new ArrayList<Map<String, Object>>();
        ArrayList<String> alistKey = new ArrayList<String>();
        List<Map<String, Object>> alistVal = new ArrayList<Map<String, Object>>();
        HashMap map = new HashMap();
        int litSize = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> tmpMap = (Map<String, Object>) list.get(i);
                // Set<Entry<String, Object>> entrySet = tmpMap.entrySet();

                for (Entry<String, Object> entry : tmpMap.entrySet()) {
                    String keyNm = entry.getKey();
                    String KeyVl = StringUtil.checkNull(entry.getValue());
                    if (litSize > 1)
                        KeyVl = padStr + KeyVl;

                    if (litSize % 2 == 0) {
                        keyValJon1 = keyValJon1 + KeyVl;
                        map.put(keyNm, keyValJon1);
                    } else {
                        keyValJon2 = keyValJon2 + KeyVl;
                        map.put(keyNm, keyValJon2);
                    }
                    litSize++;
                }
//System.out.println("======================>keyValJon1:"+keyValJon1);				
//System.out.println("======================>keyValJon2:"+keyValJon2);				
            }
            setList.add(map);
        }
        // list.addAll(setList);
        return setList;
    }

    /**
     * 이차원 배열을 받아 연결될 문자열로 연결한다 이때 각 엘레먼트 사이에 구분문자열을 추가한다.
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2008. 3. 19.
     * @param e
     * @return
     */
//    public static Map<String, Object> IBCodeMap(List<Map<String,Object>> list, @CommandMap Map<String, Object> commandMap){
//    	
//    	String keyNmTmp = "";
//    	String keyNmJon = "";
//    	String keyValJon1 = "";
//    	String keyValJon2 = "";
//    	List<Map<String,Object>>  setList = new ArrayList<Map<String,Object>>();
//        ArrayList<String> alistKey = new ArrayList<String>();
//        List<Map<String,Object>>  alistVal = new ArrayList<Map<String,Object>>();
//        HashMap map  =  new HashMap();
//
//        int litSize = 0;
//        String tmpLclC = "";
//        String tmpHnm  = "";
//        String tmpSclC = "";
//		if(list != null && list.size() > 0){
//			for(int i = 0; i < list.size(); i++){
//				Map<String, Object> tmpMap = (Map<String, Object>)list.get(i);
//				//Set<Entry<String, Object>> entrySet = tmpMap.entrySet();
//				
//				for (Entry<String, Object> entry : tmpMap.entrySet()) {
//					 String keyNm = entry.getKey();
//					 String KeyVl = StringUtil.checkNull(entry.getValue());
// 
// 					if("LCL_C".equals(keyNm) && !tmpLclC.equals(KeyVl) && litSize > 0){
// 	 					commandMap.put(tmpLclC+"_CB_NM", tmpHnm);
// 	 					commandMap.put(tmpLclC+"_CB_CD",tmpSclC);
// 	 					tmpHnm = "";
// 	 					tmpSclC = "";
// 					}else if("HNM".equals(keyNm)){
// 						tmpHnm = tmpHnm + ( !"".equals(tmpHnm) && !"|".equals(tmpHnm)?"|":"")+KeyVl;
// 					}else if("SCL_C".equals(keyNm)){
// 						tmpSclC = tmpSclC + ( !"".equals(tmpSclC) && !"|".equals(tmpSclC) ?"|":"")+KeyVl;
// 					}
//
// 					if("LCL_C".equals(keyNm) ){
//	 					tmpLclC = KeyVl;
// 					}
//
//					 litSize++;
//				}
//			}
//			commandMap.put(tmpLclC+"_CB_NM", tmpHnm);
//			commandMap.put(tmpLclC+"_CB_CD", tmpSclC);
//		}
//		//list.addAll(setList);
//		return commandMap;
//    }        

    // 입력 받은 문자를 대문자로 변경한다.
    public static char toUpperCase(char c) {
        return Character.toUpperCase(c);
    }

    // 입력받은 문자를 소문자로 변경한다.
    public static char toLowerCase(char c) {
        return Character.toLowerCase(c);
    }

    /**
     * 32자리 ID 만들기
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2008. 3. 19.
     * @param e
     * @return
     */
    public static final String generateGUID(Object o) {
        StringBuffer tmpBuffer = new StringBuffer(16);
        if (hexServerIP == null) {
            InetAddress localInetAddress = null;
            try {
                localInetAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException uhe) {
                try {
                    localInetAddress = InetAddress.getByName("localhost");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            byte serverIP[] = localInetAddress.getAddress();
            hexServerIP = hexFormat(getInt(serverIP), 8);
        }
        String hashcode = hexFormat(System.identityHashCode(o), 8);
        tmpBuffer.append(hexServerIP);
        tmpBuffer.append(hashcode);
        long timeNow = System.currentTimeMillis();
        int timeLow = (int) timeNow & -1;
        int node = -1;
        synchronized (seeder) {
            node = seeder.nextInt();
        }
        StringBuffer guid = new StringBuffer(32);
        guid.append(hexFormat(timeLow, 8));
        guid.append(tmpBuffer.toString());
        guid.append(hexFormat(node, 8));
        return guid.toString();
    }

    /**
     * UUID 만들기
     * 
     * @author jung sung hyun
     * @version 1.0
     * @modifydate 2022.1. 23.
     * @param e
     * @return
     */
    public static final String generateGUID2() {
        // UUID uuid = new UUID(24, 02);
        UUID uuid = UUID.randomUUID();
        return UUID.fromString(uuid.toString()).toString();
    }

    public static int getInt(byte bytes[]) {
        int i = 0;
        int j = 24;
        for (int k = 0; j >= 0; k++) {
            int l = bytes[k] & 0xff;
            i += l << j;
            j -= 8;
        }

        return i;
    }

    public static String hexFormat(int i, int j) {
        String s = Integer.toHexString(i);
        return padHex(s, j) + s;
    }

    private static String padHex(String s, int i) {
        StringBuffer tmpBuffer = new StringBuffer();
        if (s.length() < i) {
            for (int j = 0; j < i - s.length(); j++)
                tmpBuffer.append('0');

        }
        return tmpBuffer.toString();
    }

    // 난수구하기이...
    public static int getRandomNumber(int maxNumber) {
        // 0~ maxNumber 까지의 랜덤수
        /**
         * 
         * 2017-02-07 이은호 수정 - Random ===> SecureRandom Object 로 변경 - 금융권 보안 취약성 위배
         **/
        SecureRandom rand = new SecureRandom();
        int rNumber = rand.nextInt(maxNumber);
        return rNumber;
    }

    private static String hexServerIP = null;
    private static final SecureRandom seeder = new SecureRandom();

//    //파일사이즈 String 명칭으로 표시하기
//    public static String getFileSizeText(String size)
//    {        
//		Double	dSize  = StringUtil.parseDouble(size);
//		dSize = MathUtil.round((dSize / 1000),0);
//		if(dSize >= 1000){
//			dSize = MathUtil.round(dSize / 1000,1);
//			return dSize+"MB";
//		}else{
//			return dSize+"KB";
//		}
//    }    

//    //파일사이즈 String 명칭을 숫자로
//    public static String getFileSizeNum(String size)
//    {        
//		if(!"".equals(checkNull(size))){
//			String siz = size.substring(size.length()-2,size.length());
//			String sizNum = size.substring(0,size.length()-2);
//			Double	dSize  = StringUtil.parseDouble(sizNum);
//			dSize = (MathUtil.round(StringUtil.parseDouble(sizNum) * 1000,0));
//			
//			if("MB".equals(siz)){
//				dSize = MathUtil.round(dSize * 1000,1);
//				return ""+dSize;
//			}else if("KB".equals(siz)){
//				return ""+dSize;
//			}
//		}
//		return "";
//    }        
}
