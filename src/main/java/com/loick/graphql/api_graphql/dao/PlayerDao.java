package com.loick.graphql.api_graphql.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.loick.graphql.api_graphql.entite.Player;

public class PlayerDao {
    private final List<Player> players;

    public PlayerDao(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
    	return this.players.stream().collect(Collectors.toList());
    }

    public Player getById(String id) {
        return this.players.stream().filter(player -> player.getId().equals(id)).findFirst().orElse(null);
    }
}
