package opensource.carelab.common.context.userContext;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserContext {
    private String userEmail;
    private String userName;
    private String userPhone;
    private String userGroupCd;
    private int userStatus;
}
