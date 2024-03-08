package com.loick.graphql.api_graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
    public Player player(@Argument String id) {
    	
        return this.playerDao.getPlayer(id);
    }

    @QueryMapping
    public List<Player> players(@Argument int count, @Argument int offset) {
    	
        return this.playerDao.getPlayers(count, offset);
    }

    @SchemaMapping
    public Team team(Player player) {
        return teamDao.getTeam(player.getTeamId());
    }

    @MutationMapping
    public Player createPlayer(@Argument String firstName, @Argument String lastName, @Argument String position, @Argument String teamId) {
    	
        String newId = Integer.toString(playerDao.useNextId());

        // create player
    	Player player = new Player();
    	player.setId(newId);
    	player.setFirstName(firstName);
    	player.setLastName(lastName);
    	player.setPosition(position);
        player.setTeamId(teamId);

        // Add player to team if team exist
        if (teamDao.teamExist(teamId)) {
            teamDao.addPlayer(teamId, newId);
        }
    	
        return playerDao.createPlayer(player);
    }

    @MutationMapping
    public Player deletePlayer(@Argument String id) {
        teamDao.removePlayer(id);
        return playerDao.deletePlayer(id);
    }

    @MutationMapping
    public Player updatePlayer(@Argument String id, @Argument String firstName, @Argument String lastName, @Argument String position, @Argument String teamId) {
        Player updatedPlayer;
        if(playerDao.playerExist(id)) {
            updatedPlayer = playerDao.getPlayer(id);

            if (firstName != null)
                updatedPlayer.setFirstName(firstName);

            if (lastName != null)
                updatedPlayer.setLastName(lastName);

            if (position != null)
                updatedPlayer.setPosition(position);

            if (teamId != null) {
                updatedPlayer.setTeamId(teamId);
                teamDao.removePlayer(id);
                teamDao.addPlayer(teamId, id);
            }
                

            return playerDao.updatePlayer(id, updatedPlayer);
        }
        

        return playerDao.deletePlayer(id);
    }
}
