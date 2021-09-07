package opensource.carelab.apis.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import opensource.carelab.common.context.userContext.UserContext;
import org.json.JSONObject;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenProvider {
    private String secretKey = "NWigbkwzUufyQzROxCnsjrMY2pdNrj8hTRSpen/n89pCo+gR5R2N/ww80a1XB/i9L0pdt386Y5oTzMgvAcLy7w==";

    public String createToken(UserContext userContext) throws Exception {
        Date currentTime = new Date();
        long tokenValidTime = (24 * 60 * 60 * 1000);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userEmail", userContext.getUserEmail() == null ? JSONObject.NULL : userContext.getUserEmail())
                .put("userName", userContext.getUserName() == null ? JSONObject.NULL : userContext.getUserName())
                .put("userLevel", userContext.getUserLevel())
                .put("userPhone", userContext.getUserPhone() == null ? JSONObject.NULL : userContext.getUserPhone())
                .put("userGroupCd", userContext.getUserGroupCd() == null ? JSONObject.NULL : userContext.getUserGroupCd())
                .put("userStatus", userContext.getUserStatus())
                .put("token", userContext.getToken() == null ? JSONObject.NULL : userContext.getToken());

        String jws = Jwts.builder()
                .setSubject(jsonObject.toString())
                .signWith(key, SignatureAlgorithm.HS512)
                .setIssuedAt(currentTime)
                .setExpiration(new Date(currentTime.getTime() + tokenValidTime))
                .compact();

        return jws;
    }

    public boolean isValidToken(String strJws) throws Exception {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(strJws);
            return true;
        } catch (ExpiredJwtException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public UserContext fetchUserContextFromJws(String strJws) throws Exception {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(strJws);

            String claimsSubject = claims.getBody().getSubject();
            JSONObject jsonClaims = new JSONObject(claimsSubject);

            UserContext userContext = new UserContext();
            userContext.setUserEmail(jsonClaims.has("userEmail") && jsonClaims.isNull("userEmail") ? "" : jsonClaims.getString("userEmail"));
            userContext.setUserName(jsonClaims.has("userName") && jsonClaims.isNull("userName") ? "" : jsonClaims.getString("userName"));
            userContext.setUserPhone(jsonClaims.has("userPhone") && jsonClaims.isNull("userPhone") ? "" : jsonClaims.getString("userPhone"));
            userContext.setUserGroupCd(jsonClaims.has("userGroupCd") && jsonClaims.isNull("userGroupCd") ? "" : jsonClaims.getString("userGroupCd"));
            userContext.setUserStatus(jsonClaims.has("userStatus") && jsonClaims.isNull("userStatus") ? 0 : jsonClaims.getInt("userStatus"));
            userContext.setUserLevel(jsonClaims.has("userLevel") && jsonClaims.isNull("userLevel") ? 0 : jsonClaims.getInt("userLevel"));
            userContext.setToken(jsonClaims.has("token") && jsonClaims.isNull("token") ? "" : jsonClaims.getString("token"));

            return userContext;
        } catch (ExpiredJwtException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
