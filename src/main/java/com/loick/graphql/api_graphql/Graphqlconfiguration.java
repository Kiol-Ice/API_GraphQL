package com.loick.graphql.api_graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loick.graphql.api_graphql.dao.TeamDao;
import com.loick.graphql.api_graphql.entite.Team;

@Configuration
public class Graphqlconfiguration {
    @Bean
    public TeamDao teamDao() {
        String[] teamName = {"Paris SG" , "AS Saint Etienne", "Olympique Lyonnais", "Olympique Marseillais", "FC Barcelone", "Real Madrid", "Manchester City", "Borussia Dortmund", "FC Bayern Munich", "FC Girondins de Bordeaux"};
        String[] teamLevel = {"Internationnal", "nationnal", "nationnal", "nationnal", "internationnal", "internationnal", "internationnal","internationnal","internationnal","nationnal" };
        String[] teamColor = {"Dark Blue", "Green and White", "Blue", "Light Blue and white", "Blue and Red", "White and gold", "Light Blue", "Yellow", "White and Red", "Dark Blue"};
        List<Team> teams = new ArrayList<>();
        for (int teamId = 0; teamId < 10; ++teamId) {
            Team team = new Team();
            team.setId("" + teamId);
            team.setName(teamName[teamId]);
            team.setLeagueLevel(teamLevel[teamId]);
            team.setColor(teamColor[teamId]);
            teams.add(team);
        }
        return new TeamDao(teams);
    }	
}
