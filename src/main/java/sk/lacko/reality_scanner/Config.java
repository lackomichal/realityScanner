package sk.lacko.reality_scanner;

import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public WebClient webClient(){
        return new WebClient();
    }

}
