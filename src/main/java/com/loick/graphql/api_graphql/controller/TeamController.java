package com.loick.graphql.api_graphql.controller;

import java.util.ArrayList;
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
public class TeamController {
    @Autowired
	private TeamDao teamDao;

    @Autowired
	private PlayerDao playerDao;

    public TeamController() {
    }

    @QueryMapping
    public Team team(@Argument String id) {
        return this.teamDao.getTeam(id);
    }

    @QueryMapping
    public List<Team> teams(@Argument int count, @Argument int offset) {
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

    @MutationMapping
    public Team createTeam(@Argument String name, @Argument String leagueLevel, @Argument String color, @Argument List<String> playerId) {
    	
        String newId = Integer.toString(teamDao.useNextId());

        // create team
    	Team team = new Team();
    	team.setId(newId);
    	team.setName(name);
    	team.setLeagueLevel(leagueLevel);
    	team.setColor(color);
        team.setPlayerId(playerId);

        // set team of player if exist
        for (String pId : playerId) {
            if (playerDao.playerExist(pId)) {
                playerDao.setTeam(pId, newId);
            }
            teamDao.removePlayer(pId);
        }
        
        return teamDao.createTeam(team);
    }

    @MutationMapping
    public Team deleteTeam(@Argument String id) {
        playerDao.unsetTeam(id);
        return teamDao.deleteTeam(id);
    }

    @MutationMapping
    public Team updateTeam(@Argument String id, @Argument String name, @Argument String leagueLevel, @Argument String color, @Argument List<String> playerId) {
        Team updatedTeam;
        if (teamDao.teamExist(id)) {
            updatedTeam = teamDao.getTeam(id);
            
            if (name != null)
                updatedTeam.setName(name);

            if (leagueLevel != null)
                updatedTeam.setLeagueLevel(leagueLevel);
            
            if (color != null) 
                updatedTeam.setColor(color);

            if (playerId != null) {
                playerDao.unsetTeam(id);
                updatedTeam.setPlayerId(playerId); 
                for (String pId : playerId) {
                    playerDao.setTeam(pId, id);
                }
            }

            return teamDao.updateTeam(id, updatedTeam);
        }
        
        return null;
    }
}
