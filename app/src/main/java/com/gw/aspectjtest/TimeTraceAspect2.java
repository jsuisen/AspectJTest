package com.gw.aspectjtest;

import android.os.SystemClock;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TimeTraceAspect2 {
    // 语法：execution(@注解 访问权限 返回值的类型 包名.函数名(参数))
    // 表示：使用TimeTrace注解的任意类型返回值任意方法名（任意参数）
    //    @Pointcut("execution(@com.gw.aspectjtest.TimeTrace * *(..))")

    //    @Pointcut("execution(* androidx.appcompat.app.AppCompatActivity.on**(..))")
//    public void myPointCut() {
//
//    }
//

    //进入异常捕获块
    @Pointcut("handler(Exception+)")
    public void myPointCut3() {
    }

    @Before("myPointCut3()")
    public void dealPoint3(JoinPoint point) throws Throwable {
        Log.e("xhq", "dealPoint3-" + point.getSignature().toString());
        Log.e("xhq", "dealPoint3-" + point.getArgs()[0].toString());
        Log.e("xhq", "dealPoint3-" + point.getSourceLocation().toString());
    }

/*
    @Pointcut("execution(@com.gw.aspectjtest.TimeTrace * *(..))")
    public void myPointCut2() {
    }

    // Advance比较常用的有：Before():方法执行前,After():方法执行后,Around():代替原有逻辑
    @Around("myPointCut2()")
    public Object dealPoint2(ProceedingJoinPoint point) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        // 获取注解
        TimeTrace annotation = methodSignature.getMethod().getAnnotation(TimeTrace.class);
        String value = annotation.value();
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
*/
}
