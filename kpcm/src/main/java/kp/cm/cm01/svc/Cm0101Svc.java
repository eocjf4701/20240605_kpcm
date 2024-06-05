package kp.cm.cm01.svc;

import java.util.List;

import kp.cm.cm01.vo.Cm0101Vo;


public interface Cm0101Svc {
    // select * from Test_Table
    public List<Cm0101Vo> selectAllList() throws Exception;

    public Cm0101Vo selectOne(Cm0101Vo vo) throws Exception;

    public void insertBoard(Cm0101Vo vo) throws Exception;

    public void updateBoard(Cm0101Vo vo) throws Exception;

    public void deleteBoard(Cm0101Vo vo) throws Exception;

    public List<Cm0101Vo> selectMenuList(Cm0101Vo vo) throws Exception ;

    public Cm0101Vo selectUser(Cm0101Vo vo) throws Exception;
}