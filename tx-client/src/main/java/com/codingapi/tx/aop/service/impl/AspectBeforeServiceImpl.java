package com.codingapi.tx.aop.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.codingapi.tx.aop.bean.TxTransactionInfo;
import com.codingapi.tx.aop.bean.TxTransactionLocal;
import com.codingapi.tx.aop.service.AspectBeforeService;
import com.codingapi.tx.aop.service.TransactionServer;
import com.codingapi.tx.aop.service.TransactionServerFactoryService;
import com.codingapi.tx.model.TransactionInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Created by lorne on 2017/7/1.
 */
@Service
public class AspectBeforeServiceImpl implements AspectBeforeService {

    @Autowired
    private TransactionServerFactoryService transactionServerFactoryService;


    private Logger logger = LoggerFactory.getLogger(AspectBeforeServiceImpl.class);


    protected com.codingapi.tx.aop.bean.TxTransaction getConfigTxTransaction(ProceedingJoinPoint point, Class<?> clazz, Method thisMethod){
        TxTransaction transaction = thisMethod.getAnnotation(TxTransaction.class);

        com.codingapi.tx.aop.bean.TxTransaction bean=new com.codingapi.tx.aop.bean.TxTransaction();
        bean.setStart(transaction.isStart());
        bean.setRollbackFor(transaction.rollbackFor());
        bean.setNoRollbackFor(transaction.noRollbackFor());
        return bean;
    }

    public Object around(String groupId, ProceedingJoinPoint point) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> clazz = point.getTarget().getClass();
        Object[] args = point.getArgs();
        Method thisMethod = clazz.getMethod(method.getName(), method.getParameterTypes());

        com.codingapi.tx.aop.bean.TxTransaction transaction = getConfigTxTransaction(point,clazz,thisMethod);

        TxTransactionLocal txTransactionLocal = TxTransactionLocal.current();

        logger.debug("around--> groupId-> " +groupId+",txTransactionLocal->"+txTransactionLocal);

        TransactionInvocation invocation = new TransactionInvocation(clazz, thisMethod.getName(), thisMethod.toString(), args, method.getParameterTypes());

        TxTransactionInfo info = new TxTransactionInfo(transaction,txTransactionLocal,invocation,groupId);

        TransactionServer server = transactionServerFactoryService.createTransactionServer(info);

        return server.execute(point, info);
    }
}
