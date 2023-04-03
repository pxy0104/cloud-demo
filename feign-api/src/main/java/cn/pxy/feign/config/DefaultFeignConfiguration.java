package cn.pxy.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-04-03 15:46
 **/

public class DefaultFeignConfiguration {
    /*
    *
   client:
    config:
      default:
        logger-level: NONE
        */
    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.BASIC;
    }
}
