package me.hangyeol.crowdfunding.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.hangyeol.crowdfunding.project.domain.Funding;
import me.hangyeol.crowdfunding.user.domain.User;

import java.util.UUID;

public abstract class FundingDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class FundRequest {
        private Integer fund;
        private User user;
        private UUID projectId;

        public Funding toEntity(User user, UUID projectId) {
            return Funding.builder()
                    .fund(fund)
                    .user(user)
                    .projectId(projectId)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class totalRequest {
        private Integer totalFundingPrice;
        private Integer totalFundingPeople;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class InfoRequest {
        private Long id;
        private Integer fund;
        private User user;
        private UUID projectId;

        public InfoRequest(Long id, Integer fund, User user, UUID projectId) {
            this.id = id;
            this.fund = fund;
            this.user = user;
            this.projectId = projectId;
        }
    }

}
