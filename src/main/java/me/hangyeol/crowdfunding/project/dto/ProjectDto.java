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

@Getter
@Setter
public abstract class ProjectDto {

    @NotBlank(message = "프로젝트 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "프로젝트 설명을 입력해주세요.")
    private String explanation;

    //    private UserDto.InfoResponse user;
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDateTime;

    private Long targetFigure;

    private String openState;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class CreateRequest extends ProjectDto {

        public Project toEntity(User user) {
            return Project.builder()
                    .title(super.title)
                    .explanation(super.explanation)
                    .user(user)
                    .startDateTime(super.startDateTime)
                    .endDateTime(super.endDateTime)
                    .targetFigure(super.targetFigure)
                    .openState(super.openState)
                    .state("준비중")
                    .build();
        }

    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class InfoResponse extends ProjectDto {
        private UUID id;
        private Long targetFigure;
        private String openState;
        private String state;
        private UserDto.InfoRequest userDto;
        // 후원수
        // 후원액

        public InfoResponse(UUID id, String title, String explanation, Long targetFigure, String state, LocalDateTime startDateTime, LocalDateTime endDateTime, String openState , UserDto.InfoRequest user) {
            this.id = id;
            super.title = title;
            super.explanation = explanation;
            this.targetFigure = targetFigure;
            this.state = state;
            super.startDateTime = startDateTime;
            super.endDateTime = endDateTime;
            this.openState = openState;
            this.userDto = user;
        }

    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class UpdateRequest extends ProjectDto {
        private String id;
        private String updateTitle;
        private String updateExplanation;

        public Project toEntity(User user, InfoResponse projectDto) {
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
