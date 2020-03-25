package me.hangyeol.crowdfunding.project.domain;

import lombok.*;
import me.hangyeol.crowdfunding.project.dto.FundingDto;
import me.hangyeol.crowdfunding.user.domain.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer fund;

    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Project.class, optional = false)
    @JoinColumn(name = "project_id", referencedColumnName = "id", columnDefinition = "uuid")
    private UUID projectId;

    @Builder
    public Funding(Integer fund, User user, UUID projectId) {
        this.fund = fund;
        this.user = user;
        this.projectId = projectId;
    }

    public FundingDto.InfoRequest toFundingDto() {
        return new FundingDto.InfoRequest(id, fund, user, projectId);
    }
}
