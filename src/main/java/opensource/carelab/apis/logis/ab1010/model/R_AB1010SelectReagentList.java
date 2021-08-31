package opensource.carelab.apis.logis.ab1010.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class R_AB1010SelectReagentList {
    private int reagentSq;
    private String userEmail;
    private String categoryCd;
    private String productName;
    private String catNo;
    private String casNo;
    private String vendorCd;
    private BigDecimal capacity;
    private int locationSq;
    private String regDt;
    private String useDt;
    private String expireDt;
    private int status;
    private String insertIp;
    private String insertDt;
    private String insertEmail;
    private String modifyIp;
    private String modifyDt;
    private String modifyEmail;
    private String deleteIp;
    private String deleteDt;
    private String deleteEmail;
}
