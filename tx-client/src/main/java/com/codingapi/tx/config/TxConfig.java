package com.codingapi.tx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * create by lorne on 2017/11/13
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "codingapi.tx")
public class TxConfig {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
