package com.codingapi.tx;

import com.codingapi.tx.config.TxConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lorne on 2017/6/26.
 */

@Configuration
@ComponentScan
public class TxClientConfiguration {


    @Bean
    public TxConfig txConfig(){
        return new TxConfig();
    }


}
