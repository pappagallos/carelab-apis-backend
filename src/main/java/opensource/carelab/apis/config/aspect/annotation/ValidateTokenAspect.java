package opensource.carelab.apis.config.aspect.annotation;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import opensource.carelab.apis.config.security.JwtTokenProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class ValidateTokenAspect {
    @Around("execution(* opensource.carelab.apis..*.controller..*.*(..)) && @annotation(opensource.carelab.apis.annotation.ValidateToken)")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization");

            log.info("[TokenValidateAnnotation] Authorization : " + token);

            // [1] JwtTokenProvider 로부터 userContext를 전달받기 위해 객체 생성
            JwtTokenProvider provider = new JwtTokenProvider();

            // [2] 먼저 header로 받아온 Jws 토큰이 유효한 토큰인지 검사
            if (provider.isValidToken(token)) {
                log.info("[TokenValidateAnnotation] Token is valid.");
                proceedingJoinPoint.proceed();
            }
        } catch (JwtException e) {
            log.error("[ERROR TokenValidateAspect] Exception Error.");
            throw new JwtException(e.getMessage());
        }
    }
}
