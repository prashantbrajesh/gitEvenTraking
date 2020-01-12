package com.hackerrank.github.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private Long id;
    private String type;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "event_actor",
            joinColumns =
                    { @JoinColumn(name = "event_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "actor_id", referencedColumnName = "id") })
    private Actor actor;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "event_repo",
            joinColumns =
                    { @JoinColumn(name = "event_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "repo_id", referencedColumnName = "id") })
    private Repo repo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created_at;


}
