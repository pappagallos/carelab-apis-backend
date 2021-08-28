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

    public String createToken(UserContext userContext) {
        Date currentTime = new Date();
        long tokenValidTime = 3600 * 60 * 1000L;
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        String jws = Jwts.builder()
                .setSubject(userContext.toString())
                .signWith(key, SignatureAlgorithm.HS512)
                .setIssuedAt(currentTime)
                .setExpiration(new Date(currentTime.getTime() + tokenValidTime))
                .compact();

        return jws;
    }

    public boolean isValidToken(String strJws) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(strJws);
            return true;
        } catch (JwtException error) {
            return false;
        }
    }

    public UserContext fetchUserContextFromJws(String strJws) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(strJws);

        UserContext userContext = new UserContext();
        JSONObject jsonObject = new JSONObject(claims.getBody().getSubject());

        userContext = userContext.getClass().cast(jsonObject);

        return userContext;
    }
}
