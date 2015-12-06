package dto;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NamedQuery(name = "Event.getAll", query = "SELECT e FROM Event e ORDER BY e.startTime DESC")
@Table(name = "EVENT")
@SecondaryTable(name = "EVENT_DETAILS", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID"))
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EventType type;

    @NotNull
    @Size(min = 5, max = 25)
    private String title;

    @Size(max = 100)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_SUBJECT")
    @Valid
    private Subject subject;

    @NotNull
    @Column(table = "EVENT_DETAILS")
    private LocalDateTime startTime;

    @NotNull
    @Column(table = "EVENT_DETAILS")
    private LocalDateTime endTime;

    @PreDestroy
    private void removeSubjectFromEvent() {
        setSubject(null);
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
