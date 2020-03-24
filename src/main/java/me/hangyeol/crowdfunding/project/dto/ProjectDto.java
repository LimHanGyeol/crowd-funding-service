package me.hangyeol.crowdfunding.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.hangyeol.crowdfunding.project.domain.Project;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import java.time.LocalDateTime;

public abstract class ProjectDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class CreateRequest {
        private String title;
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
        private String title;
        private String explanation;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime startDateTime;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime endDateTime;
        private Long targetFigure;
        private String OpenState;
        private String state;
        // 창작자 이름
        // 창작자 닉네임
        // 창작자 핸드폰 번호
        // 후원수
        // 후원액

        public InfoRequest(String title, String explanation, Long targetFigure, String state, LocalDateTime startDateTime, LocalDateTime endDateTime) {
            this.title = title;
            this.explanation = explanation;
            this.targetFigure = targetFigure;
            this.state = state;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class UpdateRequest {
        private String title;
        private String explanation;
        private String openState;

        public Project toEntity() {
            return Project.builder()
                    .title(title)
                    .explanation(explanation)
                    .openState(openState)
                    .build();
        }
    }
}
