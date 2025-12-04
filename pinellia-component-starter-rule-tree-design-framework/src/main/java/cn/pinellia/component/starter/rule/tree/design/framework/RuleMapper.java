package cn.pinellia.component.starter.rule.tree.design.framework;

/**
 * @Author BanXia
 * @description: 规则映射器
 * @Date 2025/12/3 01:36
 * T 入参
 * D 上下文
 * R 输出
 */
public interface RuleMapper<T, D, R> {

    /**
     * 获取规则执行器
     * @param requestParameter 入参
     * @param dynamicContext 上下文
     * @return 规则执行器
     * @throws Exception 获取规则执行器异常
     */
    RuleHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;
}
