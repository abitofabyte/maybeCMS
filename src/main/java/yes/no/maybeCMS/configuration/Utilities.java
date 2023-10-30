package yes.no.maybeCMS.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class Utilities {
    @Bean
    public Random random() {
        return new Random();
    }
}
