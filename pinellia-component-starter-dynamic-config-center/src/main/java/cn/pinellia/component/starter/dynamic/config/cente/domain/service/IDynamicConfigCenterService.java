package cn.pinellia.component.starter.dynamic.config.cente.domain.service;

import cn.pinellia.component.starter.dynamic.config.cente.domain.model.valobj.AttributeVO;

/**
 * @Author BanXia
 * @description: DCC服务接口
 * @Date 2025/12/5 00:59
 */
public interface IDynamicConfigCenterService {

    /**
     * 代理对象
     */
    Object proxyObject(Object bean);

    /**
     * 调整属性值
     */
    void adjustAttributeValue(AttributeVO attributeVO);
}
