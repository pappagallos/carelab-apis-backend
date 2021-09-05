package opensource.carelab.apis.logis.ab1010.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class P_AB1010SelectReagentList {
    private String userEmail;
    private int pageOffset;
    private int pageLimit;
    private String search;
    private int searchFg;
    private String fromDt;
    private String toDt;
}