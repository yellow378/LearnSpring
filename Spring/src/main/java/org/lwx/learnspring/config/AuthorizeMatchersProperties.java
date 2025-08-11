package org.lwx.learnspring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "authorize")
@Data
public class AuthorizeMatchersProperties {
    /**
     * 放行白名单配置
     */
    public List<String> permit_urls;
}
