package cn.pinellia.component.starter.dynamic.config.cente.domain.model.valobj;

/**
 * @Author BanXia
 * @description: 属性VO
 * @Date 2025/12/5 01:06
 */
public class AttributeVO {

    /** 键 - 属性 fileName */
    private String attribute;

    /** 值 */
    private String value;

    public AttributeVO() {
    }

    public AttributeVO(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
