package me.hangyeol.crowdfunding.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    @GetMapping("/edit-project")
    public String home() {
        return "project";
    }
}
