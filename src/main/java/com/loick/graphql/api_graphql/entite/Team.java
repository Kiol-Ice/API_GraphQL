package com.loick.graphql.api_graphql.entite;

import java.util.List;

public class Team {
    private String id;
    private String name;
    private String leagueLevel;
    private String color;
    private List<String> idPlayers;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLeagueLevel() {
        return leagueLevel;
    }
    public void setLeagueLevel(String leagueLevel) {
        this.leagueLevel = leagueLevel;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    
    public List<String> getIdPlayers() {
        return idPlayers;
    }
    public void setIdPlayers(List<String> idPlayers) {
        this.idPlayers = idPlayers;
    }
}
