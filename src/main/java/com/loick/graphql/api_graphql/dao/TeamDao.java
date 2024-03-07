package com.loick.graphql.api_graphql.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.loick.graphql.api_graphql.entite.Team;

public class TeamDao extends BaseDao {
    private final List<Team> teams;

    public TeamDao(List<Team> teams, int nextId) {
        super(nextId);
        this.teams = teams;
    }

    public List<Team> getTeams(int count, int offset) {
    	
    	return this.teams.stream().skip(offset).limit(count).collect(Collectors.toList());
    }

    public Team getTeam(String id) {
        return this.teams.stream().filter(team -> team.getId().equals(id)).findFirst().orElse(null);
    }

    public Team createTeam(Team team) {
		this.teams.add(team);
		
		return team;
	}

    // addPlayer to team on player create
    public Team addPlayer(String teamId, String playerId) {
        Team team = getTeam(teamId);
        if (!team.getPlayerId().contains(playerId)) {
            team.getPlayerId().add(playerId);
        }
        return team;
    }

    // remove player from old team
            // Team oldTeam = teams.stream().filter(t -> t.getPlayerId().contains(playerId)).findFirst().orElse(null);
            // if (oldTeam != null) {
            //     oldTeam.getPlayerId().remove(playerId);
            // }

    public Boolean teamExist(String id) {
        return getTeam(id) != null;
    }
}
