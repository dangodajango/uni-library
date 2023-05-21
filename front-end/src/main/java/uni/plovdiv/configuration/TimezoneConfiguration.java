package uni.plovdiv.configuration;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class TimezoneConfiguration {

    @PostConstruct
    public void configureTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
