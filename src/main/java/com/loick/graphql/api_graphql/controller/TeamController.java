package com.loick.graphql.api_graphql.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.loick.graphql.api_graphql.dao.PlayerDao;
import com.loick.graphql.api_graphql.dao.TeamDao;
import com.loick.graphql.api_graphql.entite.Player;
import com.loick.graphql.api_graphql.entite.Team;

@Controller
public class TeamController {
    @Autowired
	private TeamDao teamDao;

    @Autowired
	private PlayerDao playerDao;

    public TeamController() {
    }

    @QueryMapping
    public Team Team(@Argument String id) {
        return this.teamDao.getTeam(id);
    }

    @QueryMapping
    public List<Team> Teams(@Argument int count, @Argument int offset) {
        return this.teamDao.getTeams(count, offset);
    }

    @SchemaMapping
    public List<Player> players(Team team) {
        List<Player> players = new ArrayList<>();

        if (team.getPlayerId() != null) {
            for (String playerId : team.getPlayerId()) {
                players.add(playerDao.getPlayer(playerId));
            }
        }
        
        return players;
    }
}
