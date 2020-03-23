package me.hangyeol.crowdfunding.project.service;

import me.hangyeol.crowdfunding.project.domain.ProjectRepository;
import me.hangyeol.crowdfunding.project.dto.ProjectDto;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.domain.UserRepository;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public void create(UserDto.InfoRequest sessionedUser, ProjectDto.CreateRequest projectDto) {
        User user = userRepository.findByEmail(sessionedUser.getEmail());
        projectRepository.save(projectDto.toEntity(user));
    }

    public void read() {

    }

}
