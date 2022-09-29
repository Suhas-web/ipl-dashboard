package com.suhas.ipldashboard.controller;

import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.suhas.ipldashboard.model.Team;
import com.suhas.ipldashboard.repository.MatchRepository;
import com.suhas.ipldashboard.repository.TeamRepository;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    TeamController(TeamRepository teamRepository, MatchRepository matchRepository){
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team1")
    public String sample(){
        return "Team1";
    }
    @GetMapping("/teams/{teamName}")
    public Team getTeams(@PathVariable String teamName){
        
        System.out.println("In getTeams");
        Optional<Team> Optionalteam = teamRepository.findByTeamName(teamName);
        Team team = Optionalteam.get();
        System.out.println(2);

        try {
            team.setMatches(matchRepository.setMatches(teamName, teamName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(team);

        return team;
        
    }
}
