/**
 * 
 */
package com.battleshipprepareground.domain;

import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

/**
 * @author amall3
 *
 */
@Component
public class BattleShipGame {

	private NavigableMap<String, List<Player>> players;

	public Map<String, List<Player>> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, List<Player>> players) {
		this.players = new TreeMap<String, List<Player>>(players);
	}

}
