package kp.cm.cm98.svc;

import java.util.HashMap;
import java.util.List;

import kp.cm.cm98.vo.Cm9801Vo;

public interface Cm9801Svc {
    public List<Cm9801Vo> selectCommonCodeList(List<HashMap<String, Object>> list) throws Exception;
}