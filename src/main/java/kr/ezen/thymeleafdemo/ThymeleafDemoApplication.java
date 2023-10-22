package kr.ezen.thymeleafdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication

//@SpringBootConfiguration // @Configuration(자바클래스를 이용한 설정)
//@EnableAutoConfiguration // 우리가 사용하는 라이브러리를 자동으로 등록해줌
//@ComponentScan
public class ThymeleafDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(ThymeleafDemoApplication.class, args);
        System.out.println("Hello Spring Boot!!!");
    }

}
