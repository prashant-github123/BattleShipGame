/**
 * 
 */
package com.battleshipprepareground.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author amall3
 *
 */
public class PlaceShipRequest {

	@NotBlank(message = "Game id is mandatory.")
	private String gameId;

	@NotBlank(message = "Player Id is mandatory.")
	private String playerId;

	@NotBlank(message = "Player Name is mandatory.")
	private String playerName;

	@NotBlank(message = "Ship Coordinates are mandatory.")
	private String shipCoordinates;

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

	/**
	 * @return the shipCoordinates
	 */
	public String getShipCoordinates() {
		return shipCoordinates;
	}

	/**
	 * @param shipCoordinates
	 *            the shipCoordinates to set
	 */
	public void setShipCoordinates(String shipCoordinates) {
		this.shipCoordinates = shipCoordinates;
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
