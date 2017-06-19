package com.computer.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by roper on 2017/6/12.
 */
@Component
@ConfigurationProperties(prefix = "server")
@PropertySource("classpath:application.properties")
public class ServicePropertyUtils {

    private String address;
    private String port;
    private String dns;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }
}