package dto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Entity
@NamedQuery(name = "Event.getAll", query = "SELECT e FROM Event e")
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

    @Valid
    @NotNull
    @OneToOne
    @JoinTable(joinColumns = {@JoinColumn(name = "FK_SUBJECT")})
    private Subject subject;

    @NotNull
    private Time startTime;

    @NotNull
    private Time endTime;

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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
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
