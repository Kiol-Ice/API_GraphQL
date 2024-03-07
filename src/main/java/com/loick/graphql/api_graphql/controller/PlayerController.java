package com.loick.graphql.api_graphql.controller;

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
public class PlayerController {
    @Autowired
	private PlayerDao playerDao;

    @Autowired
	private TeamDao teamDao;

    public PlayerController() {
    }

    @QueryMapping
    public List<Player> Players() {
    	
        return this.playerDao.getPlayers();
    }

    @SchemaMapping
    public Team team(Player player) {
        return teamDao.getById(player.getTeamId());
    }

}
