package me.hangyeol.crowdfunding.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.hangyeol.crowdfunding.project.domain.Project;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class ProjectDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class CreateRequest {
        @NotBlank(message = "프로젝트 제목을 입력해주세요.")
        private String title;
        @NotBlank(message = "프로젝트 설명을 입력해주세요.")
        private String explanation;
        private User user;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime startDateTime;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime endDateTime;
        private Long targetFigure;
        private String openState;

        public Project toEntity(User user) {
            return Project.builder()
                    .title(title)
                    .explanation(explanation)
                    .user(user)
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .targetFigure(targetFigure)
                    .openState(openState)
                    .state("준비중")
                    .build();
        }

    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class InfoRequest {
        private UUID id;
        private String title;
        private String explanation;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime startDateTime;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime endDateTime;
        private Long targetFigure;
        private String openState;
        private String state;
        private UserDto.InfoRequest user;
        // 후원수
        // 후원액

        public InfoRequest(UUID id, String title, String explanation, Long targetFigure, String state, LocalDateTime startDateTime, LocalDateTime endDateTime, String openState ,UserDto.InfoRequest user) {
            this.id = id;
            this.title = title;
            this.explanation = explanation;
            this.targetFigure = targetFigure;
            this.state = state;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.openState = openState;
            this.user = user;
        }

    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class UpdateRequest {
        private String id;
        private String updateTitle;
        private String updateExplanation;
        private User user;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime startDateTime;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime endDateTime;
        private Long targetFigure;
        private String openState;

        public Project toEntity(User user, ProjectDto.InfoRequest projectDto) {
            return Project.builder()
                    .id(UUID.fromString(id))
                    .title(projectDto.getTitle())
                    .explanation(projectDto.getExplanation())
                    .user(user)
                    .startDateTime(projectDto.getStartDateTime())
                    .endDateTime(projectDto.getEndDateTime())
                    .targetFigure(projectDto.getTargetFigure())
                    .openState(projectDto.getOpenState())
                    .state("준비중")
                    .build();
        }
    }
}
