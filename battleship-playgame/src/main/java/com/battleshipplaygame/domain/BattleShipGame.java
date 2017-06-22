/**
 * 
 */
package com.battleshipplaygame.domain;

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

	private String whoseTurn;
	
	private boolean isGameOver;

	private NavigableMap<String, List<Player>> players;

	public Map<String, List<Player>> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, List<Player>> players) {
		this.players = new TreeMap<String, List<Player>>(players);
	}

	/**
	 * @return the whoseTurn
	 */
	public String getWhoseTurn() {
		return whoseTurn;
	}

	/**
	 * @param whoseTurn
	 *            the whoseTurn to set
	 */
	public void setWhoseTurn(String whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

	/**
	 * @return the isGameOver
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * @param isGameOver the isGameOver to set
	 */
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

}
