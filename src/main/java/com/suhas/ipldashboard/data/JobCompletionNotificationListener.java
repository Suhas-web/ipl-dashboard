package com.suhas.ipldashboard.data;

import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.suhas.ipldashboard.model.Team;

@Component
@Transactional
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED!");
            
            //map all team names to Team objects from the stored table Match to create a new Table team
            HashMap<String, Team> mapNameToTeam = new HashMap<>();

            //Store all distinct teams from column team1 and their count in map
            List<Object[]> res = em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
              .getResultList();
            // res.forEach(t -> System.out.println(t[0]));
            res.stream().map(o -> new Team((String)o[0], (Long)o[1])).forEach(t -> mapNameToTeam.put(t.getTeamName(), t));

            // for column team2 find out the number of matches played and merge both results in map
            em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
              .getResultStream()
            //   .map(obj -> new Team((String) obj[0], (long)obj[1]))
              .forEach(obj -> {
                Team team = mapNameToTeam.get(obj[0]);
                team.setTotalMatches(team.getTotalMatches() + (long)obj[1]);
                mapNameToTeam.put(team.getTeamName(), team);
              });
            
              em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                .getResultStream()
                .forEach(obj -> {
                    Team team = mapNameToTeam.get(obj[0]);
                    if(team != null)
                    team.setTotalWins((long)obj[1]);
                });

            mapNameToTeam.forEach((teamName, team) -> em.persist(team));
            em.createQuery("select teamName,totalMatches,totalWins from Team", Object[].class)
              .getResultStream()
              .forEach(obj -> System.out.println(obj[0] + " " + obj[1] + " " + obj[2]));
        }
    }
}
