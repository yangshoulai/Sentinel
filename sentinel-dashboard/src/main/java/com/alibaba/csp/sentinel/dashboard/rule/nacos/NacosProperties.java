package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author shoulai.yang@gmail.com
 * @date 2022/1/18
 */
@ConfigurationProperties(prefix = "sentinel.nacos")
public class NacosProperties {

  private String namespace;

  private String serverAddress = "127.0.0.1:8848";

  private String groupId = "SENTINEL_GROUP";

  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public String getServerAddress() {
    return serverAddress;
  }

  public void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }
}
