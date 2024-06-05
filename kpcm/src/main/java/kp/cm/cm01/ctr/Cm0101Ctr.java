package kp.cm.cm01.ctr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.Resource;
import kp.cm.cm01.svc.Cm0101Svc;
import kp.cm.cm01.vo.Cm0101Vo;
import kp.cm.common.config.KnwpProperties;
import kp.cm.common.util.JsonUtil;
/**
 * @Project    : 차세대 지급결제플랫폼구축사업
 * @Class      : Cm0101Ctr
 * @Package    : kp.cm.cm01.ctr
 * @Description: 공통 Pilot 구현을 위한 클레스입니다.
 * @Author     : 정성현
 * @Date       : 2024년. 05월. 25일
 * @Version    : 0.1
 * 변경이 있을 때에는 수정 이ㅣ력에 변경일자와 변경자, 그리고 변경사유를 기록하여 관리가 되도록 한다.
 * ========================================================================================================
 *                                    수정 이력관리 (형상관리에도 Copy휴 반영)
 * --------------------------------------------------------------------------------------------------------
 *      수정일        수정자                                  수정내용
 * --------------------------------------------------------------------------------------------------------
 *   2024.05.15       정성현                                  최초작성
 *   2024.05.16       홍길동                     Method 수정및 추가작업
 * ========================================================================================================
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequestMapping("/kp/cm/cm01/")
public class Cm0101Ctr {

    @Resource
    private Cm0101Svc cm0101Svc;
    @Autowired
    KnwpProperties knwpProperties;
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
     * @Date  : 2024-07-07
     * @return: ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/cm0101/selectBoardList")
    public ModelAndView selectBoardList(@RequestBody Cm0101Vo vo) throws Exception {
        System.out.println("knwpProperties:"+knwpProperties.getUploadPath());//프로퍼티 테스트
        ModelAndView mav = new ModelAndView("jsonView");
        List<Cm0101Vo> AllList = cm0101Svc.selectAllList();
        mav.addObject("Alllist", AllList);
        mav.setViewName("jsonView");
        return mav;
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
     * @Date  : 2024-07-07
     * @return: ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/cm0101/selectBoard", method = RequestMethod.POST)

    public ModelAndView selectView(@RequestBody Map<String, Object> map) throws Exception {
        Cm0101Vo vo = new Cm0101Vo();
        vo = (Cm0101Vo) JsonUtil.getInstance().convertMapToObject(map, vo);
        ModelAndView mav = new ModelAndView("jsonView");
        Cm0101Vo AllList = cm0101Svc.selectOne(vo);
        mav.addObject("Alllist", AllList);
        mav.setViewName("jsonView");
        return mav;
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
     * @Date  : 2024-07-07
     * @return: ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/cm0101/insertBorad", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ModelAndView insertConten(@RequestBody HashMap<String, Object> map) throws Exception {
        // TestTableVo vo = new TestTableVo();
        Cm0101Vo vo = new Cm0101Vo();
        vo = (Cm0101Vo) JsonUtil.getInstance().convertMapToObject(map, vo);
        ModelAndView mav = new ModelAndView("jsonView");
        cm0101Svc.insertBoard(vo);
        mav.addObject("message", "성공!!");
        mav.setViewName("jsonView");
        return mav;
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
     * @Date  : 2024-07-07
     * @return: ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/cm0101/updateBoard", method = RequestMethod.POST)
    public ModelAndView updateConten(@RequestBody HashMap<String, Object> map) throws Exception {
        Cm0101Vo vo = new Cm0101Vo();
        vo = (Cm0101Vo) JsonUtil.getInstance().convertMapToObject(map, vo);
        ModelAndView mav = new ModelAndView("jsonView");
        cm0101Svc.updateBoard(vo);
        mav.addObject("message", "update 성공!!");
        mav.setViewName("jsonView");
        return mav;
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
     * @Date  : 2024-07-07
     * @return: ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/cm0101/deleteBoard", method = RequestMethod.POST)
    public ModelAndView deleteConten(@RequestBody HashMap<String, Object> map) throws Exception {
        Cm0101Vo vo = new Cm0101Vo();
        vo = (Cm0101Vo) JsonUtil.getInstance().convertMapToObject(map, vo);
        ModelAndView mav = new ModelAndView("jsonView");
        cm0101Svc.deleteBoard(vo);
        mav.addObject("message", "delete 성공!!");
        mav.setViewName("jsonView");
        return mav;
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
     * @Date  : 2024-07-07
     * @return: ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/cm0101/selectMenu", method = RequestMethod.POST)

    public ModelAndView selectMenuList(@RequestBody Map<String, Object> map) throws Exception {
        Cm0101Vo vo = new Cm0101Vo();
        vo = (Cm0101Vo) JsonUtil.getInstance().convertMapToObject(map, vo);
        ModelAndView mav = new ModelAndView("jsonView");
        List<Cm0101Vo> AllList = cm0101Svc.selectMenuList(vo);
        mav.addObject("Alllist", AllList);
        mav.setViewName("jsonView");
        System.out.println(mav);
        return mav;
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
     * @Date  : 2024-07-07
     * @return: ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/cm0101/selectUser", method = RequestMethod.POST)
    public ModelAndView selectUser(@RequestBody Map<String, Object> map) throws Exception {
        Cm0101Vo vo = new Cm0101Vo();
        vo = (Cm0101Vo) JsonUtil.getInstance().convertMapToObject(map, vo);
        ModelAndView mav = new ModelAndView("jsonView");
        Cm0101Vo AllList = cm0101Svc.selectUser(vo);
        mav.addObject("Alllist", AllList);
        mav.setViewName("jsonView");
        return mav;
    }
}
