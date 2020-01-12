package com.hackerrank.github.repository;

import com.hackerrank.github.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long>{


    @Query(value = "SELECT a.id,a.avatar_url,a.login,COUNT(*) AS Count, FROM " +
            "actor a left join event_actor ea " +
            "on ea.actor_id=a.id left join event e on e.id=ea.event_id group by a.id order by Count,e.created_at ",
            nativeQuery = true)
    List<Actor> findAllOrderByEventCountTheTime();

    // yet to do
    @Query(value = "SELECT a.id,a.avatar_url,a.login,COUNT(*) AS Count, FROM " +
            "actor a left join event_actor ea " +
            "on ea.actor_id=a.id left join event e on e.id=ea.event_id group by order by Count,e.created_at,a.login ",
            nativeQuery = true)
    List<Actor> findAllOrderByMaxSteakOrderByTheTime();

}
