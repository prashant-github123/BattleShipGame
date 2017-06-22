/**
 * 
 */
package com.battleshipplaygame.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author amall3
 *
 */
public class CheckTurnStatusRequest {

	@NotBlank(message = "Player Id is mandatory.")
	private String playerId;

	@NotBlank(message = "Game Id is mandatory.")
	private String gameId;

	@NotBlank(message = "Player Name is mandatory.")
	private String playerName;

	private String hitOrMiss;

	private boolean gameOver;

	private boolean checkTurnStatusOnly;

	private String hitCoordinate;

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

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver
	 *            the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return the checkTurnStatusOnly
	 */
	public boolean isCheckTurnStatusOnly() {
		return checkTurnStatusOnly;
	}

	/**
	 * @param checkTurnStatusOnly
	 *            the checkTurnStatusOnly to set
	 */
	public void setCheckTurnStatusOnly(boolean checkTurnStatusOnly) {
		this.checkTurnStatusOnly = checkTurnStatusOnly;
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
	 * @return the hitOrMiss
	 */
	public String getHitOrMiss() {
		return hitOrMiss;
	}

	/**
	 * @param hitOrMiss
	 *            the hitOrMiss to set
	 */
	public void setHitOrMiss(String hitOrMiss) {
		this.hitOrMiss = hitOrMiss;
	}

}
