package com.loick.graphql.api_graphql.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.loick.graphql.api_graphql.entite.Team;

public class TeamDao {
    private final List<Team> teams;

    public TeamDao(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams(int count, int offset) {
    	
    	return this.teams.stream().skip(offset).limit(count).collect(Collectors.toList());
    }

    public Team getTeam(String id) {
        return this.teams.stream().filter(team -> team.getId().equals(id)).findFirst().orElse(null);
    }
}
