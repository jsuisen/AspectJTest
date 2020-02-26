package com.gw.aspectjtest;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TimeTraceAspect {
    // 语法：execution(@注解 访问权限 返回值的类型 包名.函数名(参数))
    // 表示：使用TimeTrace注解的任意类型返回值任意方法名（任意参数）
    //    @Pointcut("execution(@com.gw.aspectjtest.TimeTrace * *(..))")

    @Pointcut("execution(* androidx.appcompat.app.AppCompatActivity.on**(..))")
    public void myPointCut() {

    }

    @Pointcut("execution(@com.gw.aspectjtest.TimeTrace * *(..))")
    public void myPointCut2(){

    }


    // Advance比较常用的有：Before():方法执行前,After():方法执行后,Around():代替原有逻辑
    @Around("myPointCut()")
    public Object dealPoint(ProceedingJoinPoint point) throws Throwable {
        // 方法执行前先记录时间
        long start = System.currentTimeMillis();
        // 执行原方法体
        Object proceed = point.proceed();

        // 方法执行完成后，记录时间，打印日志
        long end = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer
                .append("耗时：")
                .append(end - start);
        Log.e(AOPLoginUtils.TAG, stringBuffer.toString());
        return proceed;
    }

    // Advance比较常用的有：Before():方法执行前,After():方法执行后,Around():代替原有逻辑
    @Around("myPointCut2()")
    public Object dealPoint2(ProceedingJoinPoint point) throws Throwable {
        // 方法执行前先记录时间
        long start = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        // 获取注解
        TimeTrace annotation = methodSignature.getMethod().getAnnotation(TimeTrace.class);
        String value = annotation.value();

        // 执行原方法体
        Object proceed = point.proceed();

        // 方法执行完成后，记录时间，打印日志
        long end = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        if (proceed instanceof Boolean) {
            // 返回的是boolean
            if ((Boolean) proceed) {
                stringBuffer/*.append(value)*/
                        .append("成功，耗时：")
                        .append(end - start);
            } else {
                stringBuffer/*.append(value)*/
                        .append("失败，耗时：")
                        .append(end - start);
            }
        }
        Log.e(AOPLoginUtils.TAG, stringBuffer.toString());
        return proceed;
    }
}
