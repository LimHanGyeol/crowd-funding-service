package me.hangyeol.crowdfunding.project.service;

import me.hangyeol.crowdfunding.project.domain.Project;
import me.hangyeol.crowdfunding.project.domain.ProjectRepository;
import me.hangyeol.crowdfunding.project.dto.ProjectDto;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.domain.UserRepository;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    // Create, Delete 는 Result 객체 만들어서 처리 - 보류
    public ProjectDto.InfoRequest create(UserDto.InfoRequest userDto, ProjectDto.CreateRequest projectDto) {
        User user = findByEmail(userDto.getEmail());
        return projectRepository.save(projectDto.toEntity(user)).toProjectDto();
    }

    public List<ProjectDto.InfoRequest> readAll() {
        List<Project> projectList = findAll();
        List<ProjectDto.InfoRequest> projectDtoList = new ArrayList<>();

        for (Project project : projectList) projectDtoList.add(project.toProjectDto());
        return projectDtoList;
    }

    public ProjectDto.InfoRequest readDetail(String title) {
        return findByTitle(title).toProjectDto();
    }

    public ProjectDto.InfoRequest update(String title, UserDto.InfoRequest userDto ,ProjectDto.UpdateRequest projectDto) {
        UserDto.InfoRequest user = findByEmail(userDto.getEmail()).toUserDto();
        ProjectDto.InfoRequest project = findByTitle(title).toProjectDto();
        if (!user.getEmail().equals(project.getUser().getEmail())) {
            return null; // Exception Message
        }
        return projectRepository.save(projectDto.toEntity()).toProjectDto();
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

    public List<Project> findAll() {
        return projectRepository.findAll();
    }


}
