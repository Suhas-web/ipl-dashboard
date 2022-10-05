package com.suhas.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suhas.ipldashboard.model.Team;
import com.suhas.ipldashboard.model.Match;

import com.suhas.ipldashboard.repository.MatchRepository;
import com.suhas.ipldashboard.repository.TeamRepository;

@CrossOrigin
@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return (List<Team>) this.teamRepository.findAll();
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeams(@PathVariable String teamName) {

        Optional<Team> Optionalteam = teamRepository.findByTeamName(teamName);
        Team team = Optionalteam.get();

        try {
            team.setMatches(matchRepository.setMatches(teamName, teamName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(team);

        return team;

    }

    @GetMapping("/teams/{teamName}/matches/")
    public List<Match> getMatches(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return matchRepository.findByTeam1AndTeam2BetweenDates(teamName, startDate,
                endDate);
    }
}
