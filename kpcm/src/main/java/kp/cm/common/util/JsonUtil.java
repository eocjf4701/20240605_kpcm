package kp.cm.common.util;

import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * Copyright 2024 by finger
 *
 * This software is the proprietary information of Korea Health Industry
 * Development Institute. Use is subject to license terms.
 *
 * <p>
 * Title: DB Connection Util
 * </p>
 *
 * <p>
 * Description: Connection을 관리하는 유틸리티(Singleton 패턴 적용).
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2005 SystemGate Corp. All rights reserved.
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author youngsin, bae
 * @version 1.0
 * @since jdk1.4.2
 */
@Slf4j
public class JsonUtil {
    private DataSource datasource = null;
    private Context itx;
    private static JsonUtil jsonUtil;
    // private static final String lookupName = "java:comp/env/jdbc/wemsin";
    private static final String lookupName = "";

    /**
     * 싱글톤 패턴의 생성자
     * 
     * @return DBUtil
     */
    public static JsonUtil getInstance() {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }

    /**
     * Json data create
     * 
     * @return DataSource 데이터 소스
     * @throws NamingException
     */

    public String getJsonData(List<?> list, ModelMap model) {
        JSONArray jsonArray = new JSONArray();
        model.addAttribute("jsonList", jsonArray.fromObject(list));
        return jsonArray.fromObject(list).toString();
    }

    /**
     * Json data create
     * 
     * @return DataSource 데이터 소스
     * @throws NamingException
     */

    public String getJsonData(Map<?, ?> map, ModelMap model) {
        model.addAttribute("jsonList", JSONObject.fromObject(JSONSerializer.toJSON(map)));
        return JSONObject.fromObject(JSONSerializer.toJSON(map)).toString();
    }

    /*
     * 멀티체크 컬럼의 데이터를 맵에 옮겨 담는다.
     */
    public Map<String, Object> setMultiCheckData(Map<String, Object> rowmap, String saveName) {

        if (rowmap.get(saveName) != null) {
            String multicheck = (String) rowmap.get(saveName);
            String[] items = multicheck.split("\\|", -1);
            for (int i = 0; i < items.length; i++) {
                String[] item = items[i].split(":", -1);
                if ("1".equals(item[1])) {
                    rowmap.put(item[0], item[1]);
                }
            }
        }
        return rowmap;
    }

    /*
     * 멀티체크 컬럼의 데이터를 맵에 옮겨 담는다.
     */
    public Object convertMapToObject(Map<String, Object> rowmap, Object valueObj) {
        System.out.println("===============idx map" + rowmap.get("param"));
//		ObjectMapper objectMapper = new ObjectMapper();
//		Map<String, Object> map = objectMapper.convertValue(person, Map.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parmaMap = (Map<String, Object>) rowmap.get("param");
        if (parmaMap != null) {
            valueObj = objectMapper.convertValue(parmaMap, valueObj.getClass());
        } else {
            valueObj = objectMapper.convertValue(rowmap, valueObj.getClass());

        }
        return valueObj;
    }
//    List<T> parseJsonToObject(String json, Class<T> type) throws Exception;
//    List<Map<String, Object>> parseJsonToListMap(String json) throws Exception;

    public List<Map<String, Object>> parseJsonToListMap(String json) throws Exception {
//        long startTime = System.currentTimeMillis();

        Gson gson = new Gson();
        List<Map<String, Object>> data = gson.fromJson(json,
                TypeToken.getParameterized(List.class, Map.class).getType());

        // System.out.println("=== GSON_PARSER \t\tData Count: " + data.size() + ",
        // 소요시간(s): " + this.getElapsedSec(startTime));
        return data;
    }
}
