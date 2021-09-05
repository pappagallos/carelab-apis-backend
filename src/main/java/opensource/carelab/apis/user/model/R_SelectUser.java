package opensource.carelab.apis.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class R_SelectUser {
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhone;
    private int userStatus;
    private int userLevel;
}
