package kr.ezen.thymeleafdemo.service;


import kr.ezen.thymeleafdemo.dto.UserDTO;
import kr.ezen.thymeleafdemo.entity.User;
import kr.ezen.thymeleafdemo.member.UserRepository;
import kr.ezen.thymeleafdemo.type.member.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(String id){

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

    public void insertAdmin(User user){


        user.setRole(RoleType.ADMIN);
        userRepository.save(user);
    }

    public List userList(){
        List user = userRepository.findUserList();

        return user;
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
