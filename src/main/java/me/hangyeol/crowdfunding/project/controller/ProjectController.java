package me.hangyeol.crowdfunding.project.controller;

import me.hangyeol.crowdfunding.project.domain.Project;
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
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("") // 전체 조회
    public ResponseEntity<List<ProjectDto.InfoRequest>> readAll() {
        return ResponseEntity.ok().body(projectService.readAll());
    }

    @PostMapping("") // 추가 / Create, Delete 는 Result를 담는 객체를 만들어서 리턴해야 할 듯
    public ResponseEntity<ProjectDto.InfoRequest> create(ProjectDto.CreateRequest projectDto, HttpSession session) {
        UserDto.InfoRequest userDto = HttpSessionUtil.getUserSession(session);
        ProjectDto.InfoRequest project = projectService.create(userDto, projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @GetMapping("/{title}") // 완료
    public ResponseEntity<ProjectDto.InfoRequest> readDetail(@PathVariable String title) {
        return ResponseEntity.ok().body(projectService.readDetail(title));
    }
    // 해야함
    @PatchMapping("/{title}")
    public String update(@PathVariable String title, ProjectDto.UpdateRequest projectDto) {
        return "";
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String title) {
        Boolean projectResult = projectService.delete(title);
        System.out.println(projectResult);
        if (!projectResult) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
