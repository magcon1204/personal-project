package kr.ezen.thymeleafdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private String memberName;
    private int age;
}
