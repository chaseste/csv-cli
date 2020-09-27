package com.chaseste.csv.aop;

import static java.util.Objects.nonNull;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CliAspect {

    @Pointcut("within(com.chaseste.csv.cli..*)")
    public void applicationPackagePointcut() {
    }

    @Around("applicationPackagePointcut()")
    public Object verboseExec(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("\nRunning command...");

        RuntimeException failure = null;
        long elapsed = 0;
        try {
            long start = System.nanoTime();
            try {
                return joinPoint.proceed();
            } finally {
                elapsed = System.nanoTime() - start;
            }
        } catch (RuntimeException e) {
            failure = e;
            throw e;
        } finally {
            StringBuilder builder = new StringBuilder();
            builder.append("-----------------------------------------");
            builder.append(String.format("\nStatus: %s", nonNull(failure) ? "FAILED" : "OK"));
            builder.append("\n-----------------------------------------");
            builder.append(String.format("\nTotal time:  %.2f s", elapsed / Math.pow(10, 9)));
            builder.append("\n-----------------------------------------\n");

            System.out.println(builder.toString());
            if (nonNull(failure)) {
                failure.printStackTrace();
            }
        }
    }
}
