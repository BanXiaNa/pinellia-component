package cn.pinellia.component.starter.dynamic.config.cente.config;

import cn.pinellia.component.starter.dynamic.config.cente.type.common.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author BanXia
 * @description: DCC自动配置属性
 * @Date 2025/12/5 01:16
 */
@ConfigurationProperties(prefix = "pinellia.wrench.config", ignoreInvalidFields = true)
public class DynamicConfigCenterAutoProperties {

    /**
     * 系统名称
     */
    private String system;

    public String getKey(String attributeName) {
        return this.system + Constants.LINE + attributeName;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

}
