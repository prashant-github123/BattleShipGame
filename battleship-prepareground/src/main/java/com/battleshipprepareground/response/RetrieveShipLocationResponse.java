/**
 * 
 */
package com.battleshipprepareground.response;

import java.util.List;

/**
 * @author amall3
 *
 */
public class RetrieveShipLocationResponse extends Response {

	private String gameId;

	private List<RetrieveCoordinatePlayerData> players;
	
	private boolean proceedGame;

	/**
	 * @return the players
	 */
	public List<RetrieveCoordinatePlayerData> getPlayers() {
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(List<RetrieveCoordinatePlayerData> players) {
		this.players = players;
	}

	/**
	 * @return the gameId
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the proceedGame
	 */
	public boolean isProceedGame() {
		return proceedGame;
	}

	/**
	 * @param proceedGame the proceedGame to set
	 */
	public void setProceedGame(boolean proceedGame) {
		this.proceedGame = proceedGame;
	}

}
