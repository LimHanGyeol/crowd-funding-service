package me.hangyeol.crowdfunding.project.controller;

import me.hangyeol.crowdfunding.project.dto.ProjectDto;
import me.hangyeol.crowdfunding.project.service.ProjectService;
import me.hangyeol.crowdfunding.support.utils.HttpSessionUtil;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public String main() {
        return "home";
    }

    @GetMapping("/edit-project")
    public String CreatePage() {
        return "project";
    }

    @PostMapping("/edit-project")
    public String Create(ProjectDto.CreateRequest projectDto, HttpSession session) {
        UserDto.InfoRequest userDto = HttpSessionUtil.getUserSession(session);
        projectService.create(userDto, projectDto);

        return "home";
    }
}
