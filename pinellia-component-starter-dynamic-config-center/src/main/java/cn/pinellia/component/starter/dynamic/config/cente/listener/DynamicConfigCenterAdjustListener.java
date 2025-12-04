package cn.pinellia.component.starter.dynamic.config.cente.listener;

import cn.pinellia.component.starter.dynamic.config.cente.domain.model.valobj.AttributeVO;
import cn.pinellia.component.starter.dynamic.config.cente.domain.service.IDynamicConfigCenterService;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author BanXia
 * @description: DCC监听器
 * @Date 2025/12/5 01:26
 */
public class DynamicConfigCenterAdjustListener implements MessageListener<AttributeVO> {

    private final Logger log = LoggerFactory.getLogger(DynamicConfigCenterAdjustListener.class);

    private final IDynamicConfigCenterService dynamicConfigCenterService;

    public DynamicConfigCenterAdjustListener(IDynamicConfigCenterService dynamicConfigCenterService) {
        this.dynamicConfigCenterService = dynamicConfigCenterService;
    }

    @Override
    public void onMessage(CharSequence channel, AttributeVO attributeVO) {
        try {
            log.info("pinellia-wrench dcc config attribute:{} value:{}", attributeVO.getAttribute(), attributeVO.getValue());
            dynamicConfigCenterService.adjustAttributeValue(attributeVO);
        } catch (Exception e) {
            log.error("pinellia-wrench dcc config attribute:{} value:{}", attributeVO.getAttribute(), attributeVO.getValue(), e);
        }
    }
}
