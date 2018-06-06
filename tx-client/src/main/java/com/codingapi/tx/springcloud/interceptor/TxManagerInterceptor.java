package com.codingapi.tx.springcloud.interceptor;

import com.codingapi.tx.aop.service.AspectBeforeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

/**
 * Created by lorne on 2017/6/7.
 */

@Component
public class TxManagerInterceptor {

    @Autowired
    private AspectBeforeService aspectBeforeService;


    @Autowired
    private Tracer tracer;

    public Object around(ProceedingJoinPoint point) throws Throwable {
        String traceId = Long.toHexString(tracer.getCurrentSpan().getTraceId());
        String spanId = Long.toHexString(tracer.getCurrentSpan().getSpanId());
        String groupId = traceId.equals(spanId)?null:traceId;
        return aspectBeforeService.around(groupId, point);
    }
}
