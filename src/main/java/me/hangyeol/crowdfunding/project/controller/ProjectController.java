package me.hangyeol.crowdfunding.project.controller;

import me.hangyeol.crowdfunding.project.dto.ProjectDto;
import me.hangyeol.crowdfunding.project.service.ProjectService;
import me.hangyeol.crowdfunding.support.utils.HttpSessionUtil;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<List<ProjectDto.InfoRequest>> readAll() {
        return ResponseEntity.ok().body(projectService.readAll());
    }

    @PostMapping("/project") // Create, Delete 는 Result를 담는 객체를 만들어서 리턴해야 할 듯
    public String create(ProjectDto.CreateRequest projectDto, HttpSession session) {
        UserDto.InfoRequest userDto = HttpSessionUtil.getUserSession(session);
        projectService.create(userDto, projectDto);
        return "home";
    }

    @GetMapping("/{title}")
    public ResponseEntity<ProjectDto.InfoRequest> readDetail(@PathVariable String title) {
        return ResponseEntity.ok().body(projectService.readDetail(title));
    }

    @PatchMapping("/{title}")
    public String update(@PathVariable String title, ProjectDto.UpdateRequest projectDto) {
        return "";
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String title) {
        Boolean projectResult = projectService.delete(title);

        if (!projectResult) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.notFound();
        }

        return (ResponseEntity<HttpStatus>) ResponseEntity.noContent();
    }
}
