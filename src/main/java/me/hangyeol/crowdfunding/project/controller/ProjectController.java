package me.hangyeol.crowdfunding.project.controller;

import io.swagger.annotations.ApiOperation;
import me.hangyeol.crowdfunding.project.dto.FundingDto;
import me.hangyeol.crowdfunding.project.dto.ProjectDto;
import me.hangyeol.crowdfunding.project.service.ProjectService;
import me.hangyeol.crowdfunding.support.utils.HttpSessionUtils;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    private int returnIntValue(String stringToInt) {
        return Integer.parseInt(stringToInt);
    }

    @ApiOperation(value = "프로젝트 전체 조회 및 페이징")
    @GetMapping("")
    public ResponseEntity<List<ProjectDto.InfoRequest>> readAll(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
        return ResponseEntity.ok().body(projectService.readAll(returnIntValue(pageNum)));
    }

    @ApiOperation(value = "프로젝트 등록, 날짜 입력 포맷 : 2020-01-01T13:00")
    @PostMapping("") // Create, Delete 는 ResultMessage를 담는 객체를 만들어서 리턴
    public ResponseEntity<ProjectDto.InfoRequest> create(UserDto.InfoRequest userDto, ProjectDto.CreateRequest projectDto) {
        ProjectDto.InfoRequest project = projectService.create(userDto, projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @ApiOperation(value = "프로젝트 정보 확인")
    @GetMapping("/{title}")
    public ResponseEntity<ProjectDto.InfoRequest> readDetail(@PathVariable String title) {
        return ResponseEntity.ok().body(projectService.readDetail(title));
    }
    // 해야함
    @ApiOperation(value = "프로젝트 수정")
    @PatchMapping("/{title}") // 리팩토링 필요 - LocalDateTime Input 처리 필요 - UUID 연동 재확인 필요
    public ResponseEntity<ProjectDto.InfoRequest> update(@PathVariable String title, UserDto.InfoRequest user, ProjectDto.UpdateRequest projectDto) {
        User userDto = projectService.findByEmail(user.getEmail());
        ProjectDto.InfoRequest project = projectService.update(title, userDto.toUserDto(), projectDto);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @ApiOperation(value = "프로젝트 삭제")
    @DeleteMapping("/{title}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String title) {
        Boolean projectResult = projectService.delete(title);
        System.out.println(projectResult);
        if (!projectResult) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{title}/funding")
    public ResponseEntity<FundingDto.totalRequest> fundingResult(ProjectDto.InfoRequest projectDto) {
        FundingDto.totalRequest totalRequest = projectService.totalFundingData(projectDto.getTitle(), projectDto);
        return ResponseEntity.status(HttpStatus.OK).body(totalRequest);
    }
}
