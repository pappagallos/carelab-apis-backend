package opensource.carelab.apis.config.aspect.logging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    StopWatch stopWatch = new StopWatch();

    @Pointcut("execution(* opensource.carelab.apis..*.controller..*.*(..))")
    private void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String controllerName = joinPoint.getTarget().getClass().getName();
        stopWatch.start();

        log.info("[" + controllerName + "] Rest Controller Start.");
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> {
            log.info("[" + controllerName + "] Request Parameter. " + arg.toString());
        });
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        String controllerName = joinPoint.getTarget().getClass().getName();
        stopWatch.stop();

        log.info("[" + controllerName + "] Rest Controller End.");
        log.info("[" + controllerName + "] Running time is " + stopWatch.getLastTaskTimeMillis() + "ms");
    }
}
