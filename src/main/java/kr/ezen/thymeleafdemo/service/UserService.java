package kr.ezen.thymeleafdemo.service;


import kr.ezen.thymeleafdemo.entity.User;
import kr.ezen.thymeleafdemo.member.UserRepository;
import kr.ezen.thymeleafdemo.type.member.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(String id){
        System.out.println("testsettsetstest");

        // 검색결과가 없으면 빈 객체를 리턴한다.
        User findUser = userRepository.findById(id).orElseGet(()->{
            System.out.println("id = " + id);
            return new User();
        });

        return findUser;
    }

    public void insertUser(User user){


        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
}
