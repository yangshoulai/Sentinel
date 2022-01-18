package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.api;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosProperties;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shoulai.yang@gmail.com
 * @date 2022/1/18
 */
@Component("gatewayApiRuleNacosPublisher")
public class GatewayApiRuleNacosPublisher implements DynamicRulePublisher<List<ApiDefinitionEntity>> {

  @Autowired
  private ConfigService configService;
  @Autowired
  private Converter<List<ApiDefinitionEntity>, String> converter;

  @Autowired
  private NacosProperties nacosProperties;

  @Override
  public void publish(String app, List<ApiDefinitionEntity> rules) throws Exception {
    AssertUtil.notEmpty(app, "app name cannot be empty");
    if (rules == null) {
      return;
    }

    configService.publishConfig(app + NacosConfigUtil.GATEWAY_API_DATA_ID_POSTFIX,
        this.nacosProperties.getGroupId(), converter.convert(rules));
  }
}
