/**
 *
 */
package kp.cm.cm98.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kp.cm.cm98.vo.Cm9801Vo;
/**
 * @Project    : 차세대 지급결제플랫폼구축사업
 * @Class      : Cm9801Dao
 * @Package    : kp.cm.cm01.dao
 * @Description: 공통 Pilot 구현을 위한 DataObject 클레스 입니다.
 * @Author     : 정성현
 * @Date       : 2024년. 05월. 25일
 * @Version    : 0.1
 */
@Repository
@Mapper
public interface Cm9801Dao {
    //public HashMap<String, Object> selectCommonCodeOne(HashMap<String, Object> param)  throws Exception;
    public List<Cm9801Vo> selectCommonCodeList(@Param("list") List<HashMap<String, Object>> list)  throws Exception;

}
