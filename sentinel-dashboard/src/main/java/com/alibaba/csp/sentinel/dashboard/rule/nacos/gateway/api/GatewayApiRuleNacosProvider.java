package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.api;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosProperties;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shoulai.yang@gmail.com
 * @date 2022/1/18
 */
@Component("gatewayApiRuleNacosProvider")
public class GatewayApiRuleNacosProvider implements DynamicRuleProvider<List<ApiDefinitionEntity>> {

  @Autowired
  private ConfigService configService;
  @Autowired
  private Converter<String, List<ApiDefinitionEntity>> converter;

  @Autowired
  private NacosProperties nacosProperties;

  @Override
  public List<ApiDefinitionEntity> getRules(String appName) throws Exception {
    String rules = configService.getConfig(appName + NacosConfigUtil.GATEWAY_API_DATA_ID_POSTFIX,
        this.nacosProperties.getGroupId(), 3000);
    if (StringUtil.isEmpty(rules)) {
      return new ArrayList<>();
    }
    return converter.convert(rules);
  }
}
