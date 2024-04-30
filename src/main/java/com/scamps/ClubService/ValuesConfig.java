package com.scamps.ClubService;

import com.scamps.ClubService.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
})
public class ValuesConfig {
    private static Environment environment;

    ValuesConfig() {
        this.environment = null;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public static String getMessage(ResponseMessage msg) {
        if (environment == null) {
            throw new IllegalStateException("Environment not set.");
        }
        return environment.getProperty(msg.getValue());
    }

    public static String getMessage(String msg) {
        if (environment == null) {
            throw new IllegalStateException("Environment not set.");
        }
        return environment.getProperty(msg);
    }
}
