package opensource.carelab.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ApisApplicationTests {

	@Test
	void contextLoads() {
		// 안전한 비밀키 생성 유틸리티
//		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		String base64EncodedKey = "NWigbkwzUufyQzROxCnsjrMY2pdNrj8hTRSpen/n89pCo+gR5R2N/ww80a1XB/i9L0pdt386Y5oTzMgvAcLy7w==";

		// 암호화된 키 보고싶을 때
//		String secretString = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println("secretString: " + secretString);

		HashMap<String, String> userContext = new HashMap<String, String>();
		userContext.put("email", "pappagallos@kakao.com");
		userContext.put("name", "이우진");
		userContext.put("phone", "010-2134-9876");
		userContext.put("groupCd", "1000");
		userContext.put("status", "1");

		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedKey));
		String jws = Jwts.builder()
				.setSubject(userContext.toString())
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();

		System.out.println(jws);

		try {
			var result = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws);
			System.out.println(result);
			//OK, we can trust this JWT

			Jws<Claims> claimsJws = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(jws);

			System.out.println("claimsJws : " + claimsJws);
			System.out.println("claimsJwsString : " + claimsJws.getBody().values());

			JSONObject jsonObject = new JSONObject(claimsJws.getBody().getSubject());

			String phone = jsonObject.getString("phone");
			String name = jsonObject.getString("name");
			String groupCd = jsonObject.getString("groupCd");
			String email = jsonObject.getString("email");
			String status = jsonObject.getString("status");

			System.out.println("phone: " + phone);
			System.out.println("name: " + name);
			System.out.println("groupCd: " + groupCd);
			System.out.println("email: " + email);
			System.out.println("status: " + status);

		} catch (JwtException | JSONException e) {
			System.out.println("NOT auth : " + e);
			//don't trust the JWT!
		}
	}

}
