package com.computer.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by roper on 2017/6/12.
 */
@Component
@ConfigurationProperties(prefix = "image")
@PropertySource("classpath:application.properties")
public class ImagePropertyUtils {

    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}