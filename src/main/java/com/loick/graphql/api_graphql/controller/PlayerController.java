package com.loick.graphql.api_graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.loick.graphql.api_graphql.dao.PlayerDao;
import com.loick.graphql.api_graphql.entite.Player;

@Controller
public class PlayerController {
    @Autowired
	private PlayerDao playerDao;

    public PlayerController() {
    }

    @QueryMapping
    public List<Player> Players() {
    	
        return this.playerDao.getPlayers();
    }

}
