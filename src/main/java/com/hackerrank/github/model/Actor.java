package com.hackerrank.github.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
//    @Column(unique = true)
    private Long id;
    private String login;
    private String avatar_url;
    @JsonIgnore
    @OneToMany(mappedBy = "actor", orphanRemoval = true)
    private List<Event> event= new ArrayList<>();

    public void addEvent(Event event){
        this.event.add(event);
    }

}
