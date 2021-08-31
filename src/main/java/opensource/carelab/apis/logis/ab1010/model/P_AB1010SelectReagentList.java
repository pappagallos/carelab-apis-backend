package opensource.carelab.apis.logis.ab1010.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class P_AB1010SelectReagentList {
    private int page;
    private int pageCount;
    private String search;
    private int pageOffset;
}
