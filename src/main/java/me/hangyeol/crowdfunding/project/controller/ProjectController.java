package me.hangyeol.crowdfunding.project.controller;

import me.hangyeol.crowdfunding.project.dto.ProjectDto;
import me.hangyeol.crowdfunding.project.service.ProjectService;
import me.hangyeol.crowdfunding.support.utils.HttpSessionUtil;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public String list() {
        List<ProjectDto.InfoRequest> projectList = projectService.read();
        return "home";
    }

    @PostMapping("/project")
    public String create(ProjectDto.CreateRequest projectDto, HttpSession session) {
        UserDto.InfoRequest userDto = HttpSessionUtil.getUserSession(session);
        projectService.create(userDto, projectDto);
        return "home";
    }

    @GetMapping("/{title}")
    public ResponseEntity<ProjectDto.InfoRequest> readDetail(@PathVariable String title) {
        System.out.println("controller : "+title);
        //title = "testTitle1";
        //projectService.readDetail(title);
        return ResponseEntity.ok(projectService.readDetail(title));
    }
}
