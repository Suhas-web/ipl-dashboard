package com.suhas.ipldashboard.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.suhas.ipldashboard.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>{
    Optional<Team> findByTeamName(String teamName);
}
