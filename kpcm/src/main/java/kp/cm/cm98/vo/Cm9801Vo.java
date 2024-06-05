package kp.cm.cm98.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias(value = "cm9801VO")
public class Cm9801Vo {
    // input
    private String codeCd;

    // output
    private String grpCd;
    private String codeNm;
}