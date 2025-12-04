package cn.pinellia.component.starter.rule.tree.design.framework;

/**
 * @Author BanXia
 * @description: 规则路由器抽象类
 * @Date 2025/12/3 01:39
 * T 入参
 * D 上下文
 * R 输出
 */
public abstract class AbstractRuleRouter<T, D, R> implements RuleHandler<T, D, R>, RuleMapper<T, D, R>{

    protected RuleHandler<T, D, R> defaultHandler = RuleHandler.DEFAULT;

    public R router(T requestParameter, D dynamicContext) throws Exception {
        RuleHandler<T, D, R> ruleHandler = this.get(requestParameter, dynamicContext);
        return ruleHandler == null ? defaultHandler.apply(requestParameter, dynamicContext) : ruleHandler.apply(requestParameter, dynamicContext);
    }
}
