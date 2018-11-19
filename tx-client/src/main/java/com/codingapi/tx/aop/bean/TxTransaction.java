package com.codingapi.tx.aop.bean;

import org.springframework.util.CollectionUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wuzl
 * Date: 2018-10-19
 * Time: 10:11
 *
 * @author 吴志林
 */
public class TxTransaction {


    /**
     * 是否LCN事务发起方
     * @return true 是:是发起方 false 否:是参与方
     */
    private  boolean isStart;


    /**
     * 回滚异常
     * @return
     */
    private  Class<? extends Throwable>[] rollbackFor;


    /**
     * 不回滚异常
     * @return
     */
    private  Class<? extends Throwable>[] noRollbackFor;


    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public Class<? extends Throwable>[] getRollbackFor() {
        return rollbackFor;
    }

    public void setRollbackFor(Class<? extends Throwable>[] rollbackFor) {
        this.rollbackFor = rollbackFor;
    }

    public Class<? extends Throwable>[] getNoRollbackFor() {
        return noRollbackFor;
    }

    public void setNoRollbackFor(Class<? extends Throwable>[] noRollbackFor) {
        this.noRollbackFor = noRollbackFor;
    }

    public Class<? extends Throwable>[] rollbackFor() {
        if (rollbackFor!=null){
            return rollbackFor;
        }
        Class<Throwable>[] rs=new Class[0];
        return rs;
    }

    public Class<? extends Throwable>[] noRollbackFor() {
        if (noRollbackFor!=null){
            return noRollbackFor;
        }
        Class<Throwable>[] rs=new Class[0];
        return rs;
    }
}
