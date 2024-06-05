package kp.cm.cm01.svc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kp.cm.cm01.dao.Cm0101Dao;
import kp.cm.cm01.svc.Cm0101Svc;
import kp.cm.cm01.vo.Cm0101Vo;

@Service
public class Cm0101SvcImpl implements Cm0101Svc {
    @Autowired
    Cm0101Dao cm0101Dao;
    /**
     * @Discription
     * 1. 개요
     *  메소드에 대한 간단한 개요 기능등을 기술한다.
     * 2. 주요처리로직
     *  메소드에 대한 주요 처리 로직등을 기술 한다.
     * 3. 예외처리
     *  예외처리시 전처리 후처리등의 내용을 기술 한다.
     * @Author: 홍길동
     * @param : Cm0101Vo vo
     * @Date  : 2024-01-07.13
     * @return: ModelAndView
     * @throws Exception
     */
    @Override
    public List<Cm0101Vo> selectAllList() throws Exception {
        // TODO Auto-generated method stub
        return cm0101Dao.selectAllList();
    }
    /**
     * @Discription
     * 1. 개요
     *  메소드에 대한 간단한 개요 기능등을 기술한다.
     * 2. 주요처리로직
     *  메소드에 대한 주요 처리 로직등을 기술 한다.
     * 3. 예외처리
     *  예외처리시 전처리 후처리등의 내용을 기술 한다.
     * @Author: 홍길동
     * @param : Cm0101Vo vo
     * @Date  : 2024-01-07.13
     * @return: ModelAndView
     * @throws Exception
     */
    @Override
    public Cm0101Vo selectOne(Cm0101Vo vo) throws Exception {
        // TODO Auto-generated method stub
        return cm0101Dao.selectOne(vo);
    }
    /**
     * @Discription
     * 1. 개요
     *  메소드에 대한 간단한 개요 기능등을 기술한다.
     * 2. 주요처리로직
     *  메소드에 대한 주요 처리 로직등을 기술 한다.
     * 3. 예외처리
     *  예외처리시 전처리 후처리등의 내용을 기술 한다.
     * @Author: 홍길동
     * @param : Cm0101Vo vo
     * @Date  : 2024-01-07.13
     * @return: ModelAndView
     * @throws Exception
     */
    @Override
    public void insertBoard(Cm0101Vo vo) throws Exception {
        // TODO Auto-generated method stub
        cm0101Dao.insertBoard(vo);
    }
    /**
     * @Discription
     * 1. 개요
     *  메소드에 대한 간단한 개요 기능등을 기술한다.
     * 2. 주요처리로직
     *  메소드에 대한 주요 처리 로직등을 기술 한다.
     * 3. 예외처리
     *  예외처리시 전처리 후처리등의 내용을 기술 한다.
     * @Author: 홍길동
     * @param : Cm0101Vo vo
     * @Date  : 2024-01-07.13
     * @return: ModelAndView
     * @throws Exception
     */
    @Override
    public void updateBoard(Cm0101Vo vo) throws Exception {
        // TODO Auto-generated method stub
        cm0101Dao.updateBoard(vo);
    }
    /**
     * @Discription
     * 1. 개요
     *  메소드에 대한 간단한 개요 기능등을 기술한다.
     * 2. 주요처리로직
     *  메소드에 대한 주요 처리 로직등을 기술 한다.
     * 3. 예외처리
     *  예외처리시 전처리 후처리등의 내용을 기술 한다.
     * @Author: 홍길동
     * @param : Cm0101Vo vo
     * @Date  : 2024-01-07.13
     * @return: ModelAndView
     * @throws Exception
     */
    @Override
    public void deleteBoard(Cm0101Vo vo) throws Exception {
        // TODO Auto-generated method stub
        cm0101Dao.deleteBoard(vo);
    }

    /**
     * @Discription
     * 1. 개요
     *  메소드에 대한 간단한 개요 기능등을 기술한다.
     * 2. 주요처리로직
     *  메소드에 대한 주요 처리 로직등을 기술 한다.
     * 3. 예외처리
     *  예외처리시 전처리 후처리등의 내용을 기술 한다.
     * @Author: 홍길동
     * @param : Cm0101Vo vo
     * @Date  : 2024-01-07.13
     * @throws Exception
     */
    @Override
    public List<Cm0101Vo> selectMenuList(Cm0101Vo vo) throws Exception {
        // TODO Auto-generated method stub
        return cm0101Dao.selectMenuList(vo);
    }

    /**
     * @Discription
     * 1. 개요
     *  메소드에 대한 간단한 개요 기능등을 기술한다.
     * 2. 주요처리로직
     *  메소드에 대한 주요 처리 로직등을 기술 한다.
     * 3. 예외처리
     *  예외처리시 전처리 후처리등의 내용을 기술 한다.
     * @Author: 홍길동
     * @param : Cm0101Vo vo
     * @Date  : 2024-01-07.13
     * @return: ModelAndView
     * @throws Exception
     */
    @Override
    public Cm0101Vo selectUser(Cm0101Vo vo) throws Exception {
        // TODO Auto-generated method stub
        return cm0101Dao.selectUser(vo);
    }

}
