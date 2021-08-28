package opensource.carelab.common.context.requestContext;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
@ToString
public class RequestContext {
    private String userEmail;
    private String userName;
    private String userPhone;
    private String userGroupCd;
    private int userStatus;
    private String insertDt;
    private String insertIp;
    private String modifyDt;
    private String modifyIp;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date currentTime = new Date();
    String today = dateFormat.format(currentTime);

    public RequestContext() {
        this.userEmail = "";
        this.userName = "";
        this.userPhone = "";
        this.userEmail = "";
        this.userGroupCd = "";
        this.userStatus = 0;
        this.insertDt = today;
        this.insertIp = "";
        this.modifyDt = today;
        this.modifyIp = "";
    }

    public RequestContext(HashMap<String, String> userContext) {

    }
}
