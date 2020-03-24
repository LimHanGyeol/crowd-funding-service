package me.hangyeol.crowdfunding.project.service;

import me.hangyeol.crowdfunding.project.domain.Project;
import me.hangyeol.crowdfunding.project.domain.ProjectRepository;
import me.hangyeol.crowdfunding.project.dto.ProjectDto;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.domain.UserRepository;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    private Page<Project> getProjectPage(PageRequest pageRequest) {
        return projectRepository.findAll(pageRequest);
    }

    private PageRequest pageRequest(int pageNum) {
        return PageRequest.of(pageNum - 1, 10, Sort.Direction.DESC, "title");
    }

    public Page<Project> projectPage(int pageNum) {
        return getProjectPage(pageRequest(pageNum));
    }

    public List<ProjectDto.InfoRequest> readAll(int pageNum) {
        Page<Project> projectsPage = projectPage(pageNum);
        System.out.println("=============="+projectsPage.toString());
        List<ProjectDto.InfoRequest> projectDtoList = new ArrayList<>();

        for (Project project : projectsPage) {
            System.out.println("===========" + project);
            projectDtoList.add(project.toProjectDto());
        }
        return projectDtoList;
    }

    public ProjectDto.InfoRequest readDetail(String title) {
        return findByTitle(title).toProjectDto();
    }

    public ProjectDto.InfoRequest create(UserDto.InfoRequest userDto, ProjectDto.CreateRequest projectDto) {
        User user = findByEmail(userDto.getEmail());
        return projectRepository.save(projectDto.toEntity(user)).toProjectDto();
    }

    public ProjectDto.InfoRequest update(String title, UserDto.InfoRequest userDto , ProjectDto.UpdateRequest projectDto) {
        User user = findByEmail(userDto.getEmail());
        ProjectDto.InfoRequest project = findByTitle(title).toProjectDto();
        System.out.println("=================="+project.getId().toString());
        System.out.println("=================="+projectDto.getId().toString());

        if (!user.toUserDto().getEmail().equals(project.getUser().getEmail())) {
            return null; // Exception Message
        }
        if (project.getTitle().equals(title)) {
            project.setTitle(projectDto.getUpdateTitle());
            project.setExplanation(projectDto.getUpdateExplanation());
            project.setOpenState(projectDto.getOpenState());
        }
        return projectRepository.save(projectDto.toEntity(user, project)).toProjectDto();
    }

    public Boolean delete(String title) {
        Project project = findByTitle(title);
        if (project == null) {
            return false;
        } else {
            projectRepository.delete(project);
            return true;
        }
    }

    public Project findByTitle(String title) {
        return projectRepository.findByTitle(title);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Project findById(UUID id) {
        return projectRepository.findById(id).get();
    }


}
