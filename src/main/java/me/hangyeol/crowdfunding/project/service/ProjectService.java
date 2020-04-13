package me.hangyeol.crowdfunding.project.service;

import me.hangyeol.crowdfunding.project.domain.Funding;
import me.hangyeol.crowdfunding.project.domain.FundingRepository;
import me.hangyeol.crowdfunding.project.domain.Project;
import me.hangyeol.crowdfunding.project.domain.ProjectRepository;
import me.hangyeol.crowdfunding.project.dto.FundingDto;
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
    private FundingRepository fundingRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, FundingRepository fundingRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.fundingRepository = fundingRepository;
    }

    private Page<Project> getProjectPage(PageRequest pageRequest) {
        return projectRepository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(int pageNum) {
        return PageRequest.of(pageNum - 1, 10, Sort.Direction.DESC, "title");
    }

    public Page<Project> createProjectPage(int pageNum) {
        return getProjectPage(getPageRequest(pageNum));
    }

    public List<ProjectDto.InfoResponse> readAll(int pageNum) {
        Page<Project> projectsPage = createProjectPage(pageNum);
        List<ProjectDto.InfoResponse> projectDtoList = new ArrayList<>();

        for (Project project : projectsPage) projectDtoList.add(project.toProjectDto());
        return projectDtoList;
    }

    public ProjectDto.InfoResponse readDetail(String title) {
        return findByTitle(title).toProjectDto();
    }

    // 리팩토링 - User를 UserDto로 받아서 처리해보기
    public ProjectDto.InfoResponse create(UserDto.InfoRequest userDto, ProjectDto.CreateRequest projectDto) {
        User user = findByEmail(userDto.getEmail());
        return projectRepository.save(projectDto.toEntity(user)).toProjectDto();
    }

    public ProjectDto.InfoResponse update(String title, UserDto.InfoRequest userDto , ProjectDto.UpdateRequest projectDto) {
        User user = findByEmail(userDto.getEmail());
        ProjectDto.InfoResponse project = findByTitle(title).toProjectDto();
        System.out.println("=================="+project.getId().toString());
        System.out.println("=================="+projectDto.getId());

//        if (!user.toUserDto().getEmail().equals(project.getUser().toUserDto().getEmail())) {
//            return null; // Exception Message
//        }
        if (project.getTitle().equals(title)) {
            project.setTitle(projectDto.getUpdateTitle());
//            project.setTitle(projectDto.getTitle());
            project.setExplanation(projectDto.getUpdateExplanation());
//            project.setExplanation(projectDto.getExplanation());
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

    public Funding findByProjectId(UUID projectId) {
        return fundingRepository.findByProjectId(projectId);
    }

    public List<Funding> fundingFindAll() {

        return fundingRepository.findAll();
    }

    public FundingDto.totalRequest totalFundingData(String title, ProjectDto.InfoResponse projectDto) {
        System.out.println("&&&&&&&&&" + projectDto.getId());
//        List<Funding> fundingList = fundingFindAll();
//        System.out.println("&&&&&&&&&" + fundingList.toString());
        Funding funding = findByProjectId(projectDto.getId());
        System.out.println(funding);
        List<FundingDto.InfoRequest> fundingDtoList = new ArrayList<>();
        int totalFundingPrice = 0;
        int totalFundingPeople = 0;

//        for (Funding funding : fundingList) {
//            fundingDtoList.add(funding.toFundingDto());
//        }

        for (int i = 0; i < fundingDtoList.size(); i++) {
            if (fundingDtoList.get(i).getProjectId().equals(projectDto.getId())) {
                FundingDto.InfoRequest fundingValue = fundingDtoList.get(i);
                totalFundingPrice+= fundingValue.getFund();
                totalFundingPeople ++;
            }
        }
        FundingDto.totalRequest totalRequest = new FundingDto.totalRequest();
        totalRequest.setTotalFundingPeople(totalFundingPeople);
        totalRequest.setTotalFundingPrice(totalFundingPrice);
        return totalRequest;
    }

    public Project findById(UUID id) {
        return projectRepository.findById(id).get();
    }

}
