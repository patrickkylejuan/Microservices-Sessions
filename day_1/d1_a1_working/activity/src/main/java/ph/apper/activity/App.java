package ph.apper.activity;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(App.class);
        ApplicationPidFileWriter appPidWriter = new ApplicationPidFileWriter("activity.pid");
        springApplication.addListeners(appPidWriter);
        springApplication.run(args);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @RestController
    @RequestMapping("activity")
    public static class ActivityController {

        @PostMapping
        public ResponseEntity create(@RequestBody Request request){
            LOGGER.info(String.valueOf(request));
            return ResponseEntity.ok().build();

        }

        @Data
        public static class Request {
            private String action;
            private String identifier;
        }
    }

}