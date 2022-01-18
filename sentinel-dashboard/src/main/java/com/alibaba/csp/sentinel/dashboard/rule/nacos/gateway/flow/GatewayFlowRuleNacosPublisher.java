package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.flow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
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
@Component("gatewayFlowRuleNacosPublisher")
public class GatewayFlowRuleNacosPublisher implements DynamicRulePublisher<List<GatewayFlowRuleEntity>> {

  @Autowired
  private ConfigService configService;
  @Autowired
  private Converter<List<GatewayFlowRuleEntity>, String> converter;

  @Autowired
  private NacosProperties nacosProperties;

  @Override
  public void publish(String app, List<GatewayFlowRuleEntity> rules) throws Exception {
    AssertUtil.notEmpty(app, "app name cannot be empty");
    if (rules == null) {
      return;
    }
    configService.publishConfig(app + NacosConfigUtil.GATEWAY_FLOW_DATA_ID_POSTFIX,
        this.nacosProperties.getGroupId(), converter.convert(rules));
  }
}
