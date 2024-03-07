package com.loick.graphql.api_graphql.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Team> Teams() {
        return this.teamDao.getTeams();
    }

    @SchemaMapping
    public List<Player> players(Team team) {
        List<Player> players = new ArrayList<>();

        if (team.getPlayerId() != null) {
            for (String playerId : team.getPlayerId()) {
                players.add(playerDao.getById(playerId));
            }
        }
        
        return players;
    }
}
