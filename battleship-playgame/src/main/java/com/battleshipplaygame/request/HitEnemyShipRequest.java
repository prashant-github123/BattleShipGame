/**
 * 
 */
package com.battleshipplaygame.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author amall3
 *
 */
public class HitEnemyShipRequest {

	private boolean hit;

	@NotBlank(message = "Hit Coordinate is mandatory.")
	private String hitCoordinate;

	@NotBlank(message = "Player Id is mandatory.")
	private String playerId;

	@NotBlank(message = "Game Id is mandatory.")
	private String gameId;

	@NotBlank(message = "Player Name is mandatory.")
	private String playerName;

	/**
	 * @return the hit
	 */
	public boolean isHit() {
		return hit;
	}

	/**
	 * @param hit
	 *            the hit to set
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
	}

	/**
	 * @return the hitCoordinate
	 */
	public String getHitCoordinate() {
		return hitCoordinate;
	}

	/**
	 * @param hitCoordinate
	 *            the hitCoordinate to set
	 */
	public void setHitCoordinate(String hitCoordinate) {
		this.hitCoordinate = hitCoordinate;
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
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName
	 *            the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
