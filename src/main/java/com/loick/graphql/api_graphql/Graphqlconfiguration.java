package com.loick.graphql.api_graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loick.graphql.api_graphql.dao.PlayerDao;
import com.loick.graphql.api_graphql.dao.TeamDao;
import com.loick.graphql.api_graphql.entite.Player;
import com.loick.graphql.api_graphql.entite.Team;

@Configuration
public class Graphqlconfiguration {
    private List<Team> initTeams() {
        String[] teamName = {"Paris SG" , "AS Saint Etienne", "Olympique Lyonnais", "Olympique Marseillais", "FC Barcelone", "Real Madrid", "Manchester City", "Borussia Dortmund", "FC Bayern Munich", "FC Girondins de Bordeaux"};
        String[] teamLevel = {"Internationnal", "nationnal", "nationnal", "nationnal", "internationnal", "internationnal", "internationnal","internationnal","internationnal","nationnal" };
        String[] teamColor = {"Dark Blue", "Green and White", "Blue", "Light Blue and white", "Blue and Red", "White and gold", "Light Blue", "Yellow", "White and Red", "Dark Blue"};
        List<Team> teams = new ArrayList<>();
        for (int playerId = 0; playerId < 10; ++playerId) {
            Team team = new Team();
            team.setId("" + playerId);
            team.setName(teamName[playerId]);
            team.setLeagueLevel(teamLevel[playerId]);
            team.setColor(teamColor[playerId]);
            team.setPlayerId(null);
            teams.add(team);
        }

        return teams;
    }

    @Bean
    public TeamDao teamDao() {
        return new TeamDao(initTeams());
    }	

    @Bean
    public PlayerDao playerDao() {
        String[] playerFirstName = {"Kylian" , "Achraf", "Étienne", "Mathieu", "Robert", "Sergi", "Erling", "Kevin", "Harry", "Manuel"};
        String[] playerLastName = {"Mbappé", "Hakimi", "Green", "Cafaro", "Lewandowski", "Roberto", "Haaland","De Bruyne","Kane","Neuer" };
        String[] playerPosition = {"forward", "defender", "GoalKeeper", "midfielder", "forward", "defender", "forward", "midfielder", "forward", "goalkeeper"};
        String[] playerTeam = {"0", "0", "1", "1", "4", "4", "6", "6", "8", "8"};
        List<Player> players = new ArrayList<>();
        for (int playerId = 0; playerId < 10; ++playerId) {
            Player player = new Player();
            player.setId("" + playerId);
            player.setFirstName(playerFirstName[playerId]);
            player.setLastName(playerLastName[playerId]);
            player.setPosition(playerPosition[playerId]);
            player.setTeamId(playerTeam[playerId]);
            players.add(player);
        }
        return new PlayerDao(players);
    }	
}
