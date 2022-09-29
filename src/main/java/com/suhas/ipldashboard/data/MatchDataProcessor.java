package com.suhas.ipldashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

import com.suhas.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
    final private static Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(@NonNull MatchInput item) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(item.getId()));
        match.setCity(item.getCity());
        match.setDate(LocalDate.parse(item.getDate()));
        match.setPlayerOfMatch(item.getPlayer_of_match());
        match.setVenue(item.getVenue());
        setTeams(match, item);
        match.setTossWinner(item.getToss_winner());
        match.setTossDecision(item.getToss_decision());
        match.setMatchWinner(item.getWinner());
        match.setResult(item.getResult());
        match.setResultMargin(item.getResult_margin());
        match.setUmpire1(item.getUmpire1());
        match.setUmpire2(item.getUmpire2());
        log.info(match.toString());
        return match;
    }

    private static void setTeams(Match match, MatchInput item) {
        // team1 -> team playing bat in toss_decision, other team is team2
        String firstInningsTeam, secondInningsTeam;
        if (item.getToss_decision().equals("bat")) {
            firstInningsTeam = item.getToss_winner();
            secondInningsTeam = firstInningsTeam.equals(item.getTeam1()) ? item.getTeam2() : item.getTeam1();
        } else {
            secondInningsTeam = item.getToss_winner();
            firstInningsTeam = secondInningsTeam.equals(item.getTeam1()) ? item.getTeam2() : item.getTeam1();
        }
        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);
    }

}