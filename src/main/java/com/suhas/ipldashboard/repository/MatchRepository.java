package com.suhas.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suhas.ipldashboard.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    Optional<List<Match>> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    @Query("select m from Match m where (m.team1 = :team or m.team2 = :team) and (m.date between :date1 and :date2) order by m.date desc")
    List<Match> findByTeam1AndTeam2BetweenDates(
            @Param("team") String team, @Param("date1") LocalDate date1, @Param("date2") LocalDate date2);

    // List<Match> findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    // String team1,
    // LocalDate start,
    // LocalDate end,
    // String team2,
    // LocalDate startDate,
    // LocalDate endDate);

    default List<Match> setMatches(String teamName, String teamName2) throws Exception {
        Pageable pageable = PageRequest.of(0, 4);
        if (findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable).isPresent()) {
            return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable).get();
        }
        throw new Error("Unable to find matches for the team");
    };

}
