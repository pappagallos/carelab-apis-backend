package opensource.carelab.apis.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class R_SignUpData {
    private String email;
    private String name;
    private String phone;
}
