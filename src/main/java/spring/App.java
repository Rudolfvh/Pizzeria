package spring;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class App {
    public static void main(String[] args) {
        var context = SpringApplication.run(App.class,args);
    }
}
