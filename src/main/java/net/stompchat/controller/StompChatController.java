package net.stompchat.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StompChatController {
    @RequestMapping("/")
    public String login() {
        return "login";
    }
    @RequestMapping("/chat")
    public String chat(@RequestParam String username, HttpSession session, Model model) {
        session.setAttribute("username", username);
        model.addAttribute("username", username);
        return "chat";
    }
}
