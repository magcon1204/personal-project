package kr.ezen.thymeleafdemo.controller;

import kr.ezen.thymeleafdemo.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {


        return "inc/home";
    }

    @GetMapping("/test")
    public String test(Model model, HttpServletRequest request) {
        model.addAttribute("name", "jhkim");
        model.addAttribute("addr", "Seoul");

        model.addAttribute("list", Arrays.asList("kor", "usa", "china", "japan", "england"));
        model.addAttribute("cno", "111");

        model.addAttribute("member", new Member("test", 33));

        //
        request.setAttribute("pnum", 1212);

        HttpSession session = request.getSession();
        session.setAttribute("id", "jhkim");

        ServletContext application = session.getServletContext();
        application.setAttribute("email", "test@gmail.com");

        return "test";
    }

    @GetMapping("/login/register")
    public String register() {

        return "/login/register";
    }

    @GetMapping("/login/login")
    public String login() {




        return "/";
    }
}
