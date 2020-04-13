package me.hangyeol.crowdfunding.project.service;

import me.hangyeol.crowdfunding.project.domain.Funding;
import me.hangyeol.crowdfunding.project.domain.FundingRepository;
import me.hangyeol.crowdfunding.project.domain.Project;
import me.hangyeol.crowdfunding.project.domain.ProjectRepository;
import me.hangyeol.crowdfunding.project.dto.FundingDto;
import me.hangyeol.crowdfunding.project.dto.ProjectDto;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FundingService {

    private FundingRepository fundingRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public FundingService(FundingRepository fundingRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.fundingRepository = fundingRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public ProjectDto.InfoResponse funding(String title, FundingDto.FundRequest fundingDto) {
        // 프로젝트 ID 찾기
        Project project = findByTitle(title);
        // User 정보 가져오기
        User user = findByEmail(fundingDto.getUser().toUserDto().getEmail());
        // 후원 금액 확인
        // 후원 금액 저장
        fundingRepository.save(fundingDto.toEntity(user, project.toProjectDto().getId()));
        // 프로젝트 정보에 후원 내용 업데이트
        Project projectDetail = findByTitle(title);
        List<Funding> fundingList = fundingRepository.findAll();
        for (int i = 0; i < fundingList.size(); i++) {
            Funding funding = fundingList.get(i);
        }
        // 변경된 프로젝트 정보 데이터 리턴
        return findByTitle(title).toProjectDto();
    }

    public User findByEmail(String email) { return userRepository.findByEmail(email); }

    public Project findByTitle(String title) {
        return projectRepository.findByTitle(title);
    }


}
