package opensource.carelab.apis.config.aspect.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Aspect
@Component
public class ControllerProxyAspect {
    @Around("execution(* opensource.carelab.apis..*.controller..*.*(..))")
    public ResponseEntity<Object> around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("[ControllerProxyAspect] Start.");

        Object response = proceedingJoinPoint.proceed();
        log.info("[ControllerProxyAspect] Response : {}", response);

        log.info("[ControllerProxyAspect] End.");

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
