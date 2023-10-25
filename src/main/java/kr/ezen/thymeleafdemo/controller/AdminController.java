package kr.ezen.thymeleafdemo.controller;


import kr.ezen.thymeleafdemo.dto.UserDTO;
import kr.ezen.thymeleafdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/admin/userList")
    public String userList(Model model) {

        List userList = userService.userList();

        model.addAttribute("userList", userList);

        return "/admin/userList";
    }

    @GetMapping("/admin/delete/{id}")
    public String userDelete(@PathVariable String id) {
        userService.delete(id);


        return "redirect:/admin/userList";
    }
}
