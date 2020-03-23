package me.hangyeol.crowdfunding.project.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.hangyeol.crowdfunding.user.domain.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String explanation;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    private Long targetFigure;

    @Column(nullable = false)
    private String openState;

    @Column(nullable = false)
    private String state;

    public String getFormattedDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00"));
    }
    //DateTimeFormatter.ofPattern("yyyy-MM-dd. HH:mm:ss")

    @Builder
    public Project(String title, String explanation, User user, LocalDateTime startDateTime, LocalDateTime endDateTime, Long targetFigure, String openState, String state) {
        this.title = title;
        this.explanation = explanation;
        this.user = user;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.targetFigure = targetFigure;
        this.openState = openState;
        this.state = state;
    }
}
