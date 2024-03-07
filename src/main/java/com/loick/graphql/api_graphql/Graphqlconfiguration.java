package com.loick.graphql.api_graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loick.graphql.api_graphql.dao.PlayerDao;
import com.loick.graphql.api_graphql.dao.TeamDao;
import com.loick.graphql.api_graphql.entite.Player;
import com.loick.graphql.api_graphql.entite.Team;

@Configuration
public class Graphqlconfiguration {
    @Bean
    public TeamDao teamDao() {
        List<Player> players = initPlayer();

        String[] teamName = {"Paris SG" , "AS Saint Etienne", "Olympique Lyonnais", "Olympique Marseillais", "FC Barcelone", "Real Madrid", "Manchester City", "Borussia Dortmund", "FC Bayern Munich", "FC Girondins de Bordeaux"};
        String[] teamLevel = {"Internationnal", "nationnal", "nationnal", "nationnal", "internationnal", "internationnal", "internationnal","internationnal","internationnal","nationnal" };
        String[] teamColor = {"Dark Blue", "Green and White", "Blue", "Light Blue and white", "Blue and Red", "White and gold", "Light Blue", "Yellow", "White and Red", "Dark Blue"};
        List<Team> teams = new ArrayList<>();
        for (int TeamId = 0; TeamId < 10; ++TeamId) {
            Team team = new Team();
            team.setId("" + TeamId);
            team.setName(teamName[TeamId]);
            team.setLeagueLevel(teamLevel[TeamId]);
            team.setColor(teamColor[TeamId]);
            team.setPlayerId(players.stream().filter(player -> player.getTeamId().equals(team.getId())).map(player->player.getId()).collect(Collectors.toList()));
            teams.add(team);
        }
        return new TeamDao(teams);
    }	

    @Bean
    public PlayerDao playerDao() {
        return new PlayerDao(initPlayer());
    }

    private List<Player> initPlayer() {
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
        return players;
    }
}
