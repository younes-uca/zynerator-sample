package ma.zs.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneratorApplication {

    public static String front = "angular";

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }

}
