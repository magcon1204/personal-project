package kr.ezen.thymeleafdemo.controller;

import kr.ezen.thymeleafdemo.dto.Member;
import kr.ezen.thymeleafdemo.dto.ResponseDTO;
import kr.ezen.thymeleafdemo.dto.UserDTO;
import kr.ezen.thymeleafdemo.entity.User;
import kr.ezen.thymeleafdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;




@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public String home() {


        return "inc/home";
    }

//    @GetMapping("/test")
//    public String test(Model model, HttpServletRequest request) {
//        model.addAttribute("name", "jhkim");
//        model.addAttribute("addr", "Seoul");
//
//        model.addAttribute("list", Arrays.asList("kor", "usa", "china", "japan", "england"));
//        model.addAttribute("cno", "111");
//
//        model.addAttribute("member", new Member("test", 33));
//
//        //
//        request.setAttribute("pnum", 1212);
//
//        HttpSession session = request.getSession();
//        session.setAttribute("id", "jhkim");
//
//        ServletContext application = session.getServletContext();
//        application.setAttribute("email", "test@gmail.com");
//
//        return "test";
//    }

    @GetMapping("/login/register")
    public String register() {

        return "/login/register";
    }

    @GetMapping("/login/adminRegister")
    public String adminRegister() {

        return "/login/adminRegister";
    }

    // 회원가입
    @PostMapping("/login/register")
    public @ResponseBody ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO) {
//        userService.insertUser(user);
//        return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원 가입 성공 완료!!");

        User user = modelMapper.map(userDTO, User.class);
        System.out.println("user.getId() = " + user.getId());

        // 아이디 중복체크
        User findUser = userService.getUser(user.getId());
        System.out.println("findUser.getId() = " + findUser.getId());

        if (findUser.getId() == null){
            userService.insertUser(user);

            return new ResponseDTO<>(HttpStatus.OK.value(),user.getUsername()+"님 회원가입 성공했습니다!!");
        }else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername()+"님은 이미 회원이십니다");
        }
    }

    // 관리자 회원가입
    @PostMapping("/login/adminRegister")
    public @ResponseBody ResponseDTO<?> insertAdmin(@Valid @RequestBody UserDTO userDTO) {
//        userService.insertUser(user);
//        return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원 가입 성공 완료!!");

        User user = modelMapper.map(userDTO, User.class);
        System.out.println("user.getId() = " + user.getId());

        // 아이디 중복체크
        User findUser = userService.getUser(user.getId());
        System.out.println("findUser.getId() = " + findUser.getId());

        if (findUser.getId() == null){
            userService.insertAdmin(user);

            return new ResponseDTO<>(HttpStatus.OK.value(),user.getUsername()+"님 회원가입 성공했습니다!!");
        }else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername()+"님은 이미 회원이십니다");
        }
    }

    @GetMapping("/login/login")
    public String login() {

        return "/login/login";
    }

    // 로그인 인증 처리
    @PostMapping("/login/login")
    public @ResponseBody ResponseDTO<?> login(@RequestBody User user
            , HttpSession session) {
        User findUser = userService.getUser(user.getId());

        // 검색결과 유무와 사용자가 입력한 비밀번호 검증
        if (findUser.getId() == null) {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
                    "아이디가 존재하지 않습니다!!");
        } else {
            // 비번 검증
            if (user.getPassword().equals(findUser.getPassword())) {
                session.setAttribute("principal", findUser);


                return new ResponseDTO<>(HttpStatus.OK.value(), findUser.getUsername() +
                        "님 로그인 성공했습니다!!");
            } else {
                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
                        "비밀번호 오류");
            }
        }
    }

    // 로그아웃
    @GetMapping("/login/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
