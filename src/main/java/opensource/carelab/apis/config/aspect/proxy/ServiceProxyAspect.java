package opensource.carelab.apis.config.aspect.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceProxyAspect {
    @Around("execution (* opensource.carelab.apis..*.service..*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            log.info("[ServiceProxyAspect] Start.");
            Object response = proceedingJoinPoint.proceed();
            log.info("[ServiceProxyAspect] End.");

            return response;
        } catch (RuntimeException e) {
            log.error("[ERROR] ServiceProxyAspect Exception Error.");
            throw new RuntimeException(e);
        }
    }
}