package me.hangyeol.crowdfunding.project.controller;

import me.hangyeol.crowdfunding.project.domain.Project;
import me.hangyeol.crowdfunding.project.domain.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/edit-project")
    public String home() {
        return "project";
    }

    @PostMapping("/edit-project")
    public String projectCreate(String title, String explanation, String startDateTime, String endDateTime, Long targetFigure, String openState) {
        Project project = new Project(title, explanation, startDateTime, endDateTime, targetFigure, openState, "진행중");
        projectRepository.save(project);

        return "home";
    }
}
