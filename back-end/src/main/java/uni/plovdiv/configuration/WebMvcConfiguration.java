package uni.plovdiv.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class WebMvcConfiguration {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @PostConstruct
    public void setupTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
