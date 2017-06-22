/**
 * 
 */
package com.battleshipregistration.response;

/**
 * @author amall3
 *
 */
public class AddPlayerResponse extends Response {

	private String gameId;

	private String playerId;

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
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

}
