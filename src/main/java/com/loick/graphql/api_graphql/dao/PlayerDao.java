package com.loick.graphql.api_graphql.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.loick.graphql.api_graphql.entite.Player;

public class PlayerDao extends BaseDao {
    private final List<Player> players;

    public PlayerDao(List<Player> players, int nextId) {
        super(nextId);
        this.players = players;
    }

    public List<Player> getPlayers(int count, int offset) {
    	return this.players.stream().skip(offset).limit(count).collect(Collectors.toList());
    }

    public Player getPlayer(String id) {
        return this.players.stream().filter(player -> player.getId().equals(id)).findFirst().orElse(null);
    }

    public Player createPlayer(Player player) {
		this.players.add(player);
		
		return player;
	}

    public Player setTeam(String playerId, String teamId) {
        Player player = getPlayer(playerId);
        player.setTeamId(teamId);
        return player;
    }

    public void unsetTeam(String teamId) {
        this.players.stream().filter(player -> player.getTeamId().equals(teamId)).forEach(player -> player.setTeamId(null));
    }

    public Player deletePlayer(String id) {
        Player playerToDelete = getPlayer(id);
        this.players.remove(playerToDelete);
        return null;
    }

    public Player updatePlayer(String id, Player player) {
        int index = this.players.indexOf(getPlayer(id));
        this.players.set(index, player);
        return player;
    }

    public Boolean playerExist(String id) {
        return getPlayer(id) != null;
    }
}
