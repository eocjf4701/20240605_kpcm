package kp.cm.common.util;

import java.util.*;

public interface JsonParserComp<T> {

    List<T> parseJsonToObject(String json, Class<T> type) throws Exception;

    List<Map<String, Object>> parseJsonToListMap(String json) throws Exception;

    // 테스트용.. 귀찮아서 그냥 인터페이스에 넣음
    default double getElapsedSec(long startTime) {
        long thisTime = System.currentTimeMillis();
        return (thisTime - startTime) / 1000D;
    }
}
