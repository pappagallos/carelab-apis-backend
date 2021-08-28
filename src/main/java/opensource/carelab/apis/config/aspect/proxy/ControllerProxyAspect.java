package opensource.carelab.apis.config.aspect.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerProxyAspect {
    @Around("execution(* opensource.carelab.apis..*.controller..*.*(..))")
    public ResponseEntity<Object> around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("[ControllerProxyAspect] Access.");
        return new ResponseEntity(proceedingJoinPoint.proceed(), HttpStatus.OK);
    }
}