package kit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;


import static org.springframework.boot.SpringApplication.run;

/**
 * Starting point of the application
 */
@SpringBootApplication
@EnableAsync
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class Application extends AsyncConfigurerSupport{


    public static void main(String[] args) {
        run(Application.class, args);
    }


}