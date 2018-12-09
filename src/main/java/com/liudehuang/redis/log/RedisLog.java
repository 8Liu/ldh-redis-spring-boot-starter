package com.liudehuang.redis.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author liudehuang
 * @date 2018/12/9 16:53
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Component
public class RedisLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLog.class);

    @Around("execution(* com.liudehuang.redis.client.*.*(..))")
    public Object logPrint(ProceedingJoinPoint pjp)
            throws Throwable {
        Object proceed = pjp.proceed();
        //获取方法上的参数
        Object[] params = pjp.getArgs();
        StringBuffer stringBuffer = new StringBuffer();
        if ((params != null) && (params.length > 0)) {
            for (Object object : params) {
                if ((object instanceof String[])) {
                    String[] param = (String[]) object;
                    if ((param != null) && (param.length > 0)) {
                        stringBuffer.append("[");
                        for (Object object1 : param) {
                            stringBuffer.append(object1);
                            stringBuffer.append(",");
                        }
                        stringBuffer.replace(stringBuffer.lastIndexOf(","), stringBuffer.lastIndexOf(",") + 1, "");
                        stringBuffer.append("]");
                    }
                } else {
                    stringBuffer.append(object);
                    stringBuffer.append(",");
                }
            }
        }
        if ((stringBuffer != null) && ("".equals(stringBuffer.toString().trim())) && (stringBuffer.length() - stringBuffer.lastIndexOf(",") == 1) &&
                (stringBuffer.lastIndexOf(",") != -1)) {
            stringBuffer.replace(stringBuffer.lastIndexOf(","), stringBuffer.lastIndexOf(",") + 1, "");
        }
        LOGGER.info("liudehuangRedisLogger\nliudehuangRedisLogger==> Redis_Preparing: {} \nliudehuangRedisLogger==> Redis_Parameters: {} \nliudehuangRedisLogger<== Redis_Result: {}", new Object[]{pjp.getSignature(), stringBuffer.toString(), proceed});
        return proceed;
    }
}
