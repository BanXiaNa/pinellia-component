package cn.pinellia.wrech.starter.dynamic.config.cente.config;

import cn.pinellia.wrech.starter.dynamic.config.cente.domain.model.valobj.AttributeVO;
import cn.pinellia.wrech.starter.dynamic.config.cente.domain.service.DynamicConfigCenterService;
import cn.pinellia.wrech.starter.dynamic.config.cente.domain.service.IDynamicConfigCenterService;
import cn.pinellia.wrech.starter.dynamic.config.cente.listener.DynamicConfigCenterAdjustListener;
import cn.pinellia.wrech.starter.dynamic.config.cente.type.common.Constants;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author BanXia
 * @description: redis 配置
 * @Date 2025/12/5 01:24
 */
@Configuration
@EnableConfigurationProperties(value = {
        DynamicConfigCenterAutoProperties.class,
        DynamicConfigCenterRegisterAutoProperties.class})
public class DynamicConfigCenterRegisterAutoConfig {

    private final Logger log = LoggerFactory.getLogger(DynamicConfigCenterRegisterAutoConfig.class);

    /**
     * redisson 客户端初始化
     * @param properties 配置类
     * @return redisson 客户端
     */
    @Bean("pinelliaWrenchRedissonClient")
    public RedissonClient redissonClient(DynamicConfigCenterRegisterAutoProperties properties) {
        Config config = new Config();
        config.setCodec(JsonJacksonCodec.INSTANCE);

        config.useSingleServer()
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
                .setPassword(properties.getPassword())
                .setConnectionPoolSize(properties.getPoolSize())
                .setConnectionMinimumIdleSize(properties.getMinIdleSize())
                .setIdleConnectionTimeout(properties.getIdleTimeout())
                .setConnectTimeout(properties.getConnectTimeout())
                .setRetryAttempts(properties.getRetryAttempts())
                .setRetryInterval(properties.getRetryInterval())
                .setPingConnectionInterval(properties.getPingInterval())
                .setKeepAlive(properties.isKeepAlive())
        ;

        RedissonClient redissonClient = Redisson.create(config);

        log.info("pinellia-wrench，注册器（redis）链接初始化完成。{} {} {}", properties.getHost(), properties.getPoolSize(), !redissonClient.isShutdown());

        return redissonClient;
    }


    /**
     * DCC 服务初始化
     * @param dynamicConfigCenterAutoProperties 配置类
     * @param pinelliaWrenchRedissonClient redisson 客户端
     * @return DCC 服务
     */
    @Bean
    public IDynamicConfigCenterService dynamicConfigCenterService(DynamicConfigCenterAutoProperties dynamicConfigCenterAutoProperties, RedissonClient pinelliaWrenchRedissonClient) {
        return new DynamicConfigCenterService(dynamicConfigCenterAutoProperties, pinelliaWrenchRedissonClient);
    }

    /**
     * MessageListener 监听初始化
     * @param dynamicConfigCenterService DCC 服务
     * @return DCC 监听
     */
    @Bean
    public DynamicConfigCenterAdjustListener dynamicConfigCenterAdjustListener(IDynamicConfigCenterService dynamicConfigCenterService) {
        return new DynamicConfigCenterAdjustListener(dynamicConfigCenterService);
    }

    /**
     * RTopic 初始化
     * @param dynamicConfigCenterAutoProperties DCC配置信息
     * @param redissonClient redisson 链接
     * @param dynamicConfigCenterAdjustListener DCC 监听
     * @return DCC 监听
     */
    @Bean(name = "dynamicConfigCenterRedisTopic")
    public RTopic dynamicConfigCenterRedisTopic(DynamicConfigCenterAutoProperties dynamicConfigCenterAutoProperties,
                                                RedissonClient redissonClient,
                                                DynamicConfigCenterAdjustListener dynamicConfigCenterAdjustListener) {
        RTopic topic = redissonClient.getTopic(Constants.getTopic(dynamicConfigCenterAutoProperties.getSystem()));
        topic.addListener(AttributeVO.class, dynamicConfigCenterAdjustListener);
        return topic;
    }

}
