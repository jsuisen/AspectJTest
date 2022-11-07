package com.gw.aspectjtest;

import android.os.SystemClock;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TimeTraceAspect {

    @Pointcut("within(@com.gw.aspectjtest.TimeTrace *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(@com.gw.aspectjtest.TimeTrace * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }  //方法切入点

    @Around("method() && @annotation(timeTrace)")//在连接点进行方法替换
    public Object aroundJoinPoint(final ProceedingJoinPoint point, TimeTrace timeTrace) throws Throwable {
        // 获取注解
        String value = timeTrace.value();
        Log.e("xhq", "value:" + value);

        // 方法执行前先记录时间
        long start = SystemClock.elapsedRealtime();
        // 执行原方法体，返回值是拦截方法的原返回值
        Object proceed = point.proceed();
        // 方法执行完成后，记录时间，打印日志
        long end = SystemClock.elapsedRealtime();

        String result = null;
        if (proceed instanceof Boolean) {
            // 返回的是boolean
            if ((Boolean) proceed) {
                result = value + " 成功，耗时：" + (end - start);
            } else {
                result = value + " 失败，耗时：" + (end - start);
            }
        }
        Log.e("xhq", "result:" + result);
        return proceed;
    }

}
