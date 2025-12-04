package cn.pinellia.component.starter.rule.tree.design.framework;

/**
 * @Author BanXia
 * @description: 规则执行器
 * @Date 2025/12/3 01:33
 * T 入参
 * D 上下文
 * R 返回值
 */
public interface RuleHandler <T, D, R>{

    RuleHandler DEFAULT = (T, D) -> null;

    /**
     * 规则处理
     * @param requestParameter 入参
     * @param dynamicContext 上下文
     * @return 处理结果
     * @throws Exception 处理异常
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;
}
