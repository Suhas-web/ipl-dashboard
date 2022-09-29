package com.suhas.ipldashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.suhas.ipldashboard.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long>{

    Optional<List<Match>> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);
   
    default List<Match> setMatches(String teamName, String teamName2) throws Exception{
        Pageable pageable = PageRequest.of(0, 4);
        if(findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable).isPresent()){
            return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable).get();
        }
        throw new Error("Unable to find matches for the team");
    };

}
