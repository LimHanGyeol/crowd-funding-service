package me.hangyeol.crowdfunding.project.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(nullable = false)
    private String startDateTime;

    @Column(nullable = false)
    private String endDateTime;

    @Column(nullable = false)
    private Long targetFigure;

    @Column(nullable = false)
    private String openState;

    @Column(nullable = false)
    private String state;

    public Project(String title, String explanation, String startDateTime, String endDateTime, Long targetFigure, String openState, String state) {
        this.title = title;
        this.explanation = explanation;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.targetFigure = targetFigure;
        this.openState = openState;
        this.state = state;
    }
}
