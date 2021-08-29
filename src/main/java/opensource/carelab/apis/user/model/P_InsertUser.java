package opensource.carelab.apis.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class P_InsertUser {
    private String email;
    private String password;
    private String name;
    private String phone;
}
