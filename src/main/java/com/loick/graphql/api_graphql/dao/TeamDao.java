package com.loick.graphql.api_graphql.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.loick.graphql.api_graphql.entite.Team;

public class TeamDao {
    private final List<Team> teams;

    public TeamDao(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
    	
    	return this.teams.stream().collect(Collectors.toList());
    }

    public Team getById(String id) {
        return this.teams.stream().filter(team -> team.getId().equals(id)).findFirst().orElse(null);
    }
}
