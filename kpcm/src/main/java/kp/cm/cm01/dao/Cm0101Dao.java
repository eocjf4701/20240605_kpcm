/**
 *
 */
package kp.cm.cm01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kp.cm.cm01.vo.Cm0101Vo;
/**
 * @Project    : 차세대 지급결제플랫폼구축사업
 * @Class      : Cm0101Dao
 * @Package    : kp.cm.cm01.dao
 * @Description: 공통 Pilot 구현을 위한 DataObject 클레스 입니다.
 * @Author     : 정성현
 * @Date       : 2024년. 05월. 25일
 * @Version    : 0.1
 */
@Repository
@Mapper
public interface Cm0101Dao {
    public List<Cm0101Vo> selectAllList()  throws Exception;
    public Cm0101Vo selectOne(Cm0101Vo vo) throws Exception;
    public void insertBoard(Cm0101Vo vo)   throws Exception;
    public void updateBoard(Cm0101Vo vo)   throws Exception;
    public void deleteBoard(Cm0101Vo vo)   throws Exception;
    public List<Cm0101Vo> selectMenuList(Cm0101Vo vo) throws Exception;
    public Cm0101Vo selectUser(Cm0101Vo vo) throws Exception;
}
