package kp.cm.cm01.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias(value = "cm0101VO")
public class Cm0101Vo {
    private int    id      ;//그리드 리스트 ID 반드시필요
    private String userCode;//사용자코드
    private String userName;//사용자명
    private int    idx     ;//게시글인텍스
    private String title   ;//게시글제목
    private String contents;//게시글내용
    private String updDt   ;//수정일자

    //메뉴관리
    private String menuNms   ;//들여쓰기 메뉴명
    private int    levels    ;//함수레벨표시
    private String menuId    ;//메뉴아이디
    private String sysSeCd   ;//시스템구분코드
    private String upMenuId  ;//상위메뉴아이디
    private String menuNm    ;//메뉴명
    private int    menuSeq   ;//메뉴순번
    private String menuExpln ;//메뉴설명
    private String menuLvl   ;//메뉴레벨
    private String prgrmId   ;//프로그램아이디
    private String prgrmPath ;//프로그램경로명
    private String urlAddr   ;//URL주소
    private String useYn     ;//사용여부

    //회원정보
    private String mbrId              ;//회원아이디
    private String mbrNm              ;//회원명
    private String mblTelno           ;//휴대전화번호
    private String mbrEmlAddr         ;//회원이메일주소
    private String ciVl               ;//CI값
    private String joinDt             ;//가입일시
    private String mbrSttsCd          ;//회원상태코드
    private String prvcClctAgreYn     ;//개인정보수집동의여부
    private String pltfUtztnTrmsAgreYn;//플랫폼이용약관동의여부
    private String mngrYn             ;//관리자여부
    private String acntClsgYn         ;//계정폐쇄여부
    private String rgtrId             ;//등록자아이디
    private String regDt              ;//등록일시
    private String rgtrIpAddr         ;//등록자IP주소
    private String mdfrId             ;//수정자아이디
    private String mdfcnDt            ;//수정일시
    private String mdfrIpAddr         ;//수정자IP주소
    private String bzmnSeCd           ;//사업자구분코드
    private String passNo             ;//비밀번호

}