package cn.pinellia.component.starter.rule.tree.design.framework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @Author BanXia
 * @description: 异步数据加载抽象类
 * @Date 2025/12/3 01:48
 */
public abstract class AbstractMultiThreadRuleRouter<T, D, R> implements RuleHandler<T, D, R>, RuleMapper<T, D, R>{

    protected RuleHandler<T, D, R> defaultHandler = RuleHandler.DEFAULT;

    public R router(T requestParameter, D dynamicContext) throws Exception {
        RuleHandler<T, D, R> ruleHandler = this.get(requestParameter, dynamicContext);
        return ruleHandler == null ? defaultHandler.apply(requestParameter, dynamicContext) : ruleHandler.apply(requestParameter, dynamicContext);
    }

    @Override
    public R apply (T requestParameter, D dynamicContext) throws Exception {
        // 异步数据加载
        multiThread(requestParameter, dynamicContext);
        // 逻辑处理
        return doApply(requestParameter, dynamicContext);
    }

    /**
     * 异步加载数据
     */
    protected abstract void multiThread(T requestParameter, D dynamicContext) throws ExecutionException, InterruptedException, TimeoutException;

    /**
     * 业务流程受理
     */
    protected abstract R doApply(T requestParameter, D dynamicContext) throws Exception;


}
