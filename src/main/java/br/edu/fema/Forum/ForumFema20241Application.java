package br.edu.fema.Forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ForumFema20241Application {

    public static void main(String[] args) {
        SpringApplication.run(ForumFema20241Application.class, args);
    }

}
