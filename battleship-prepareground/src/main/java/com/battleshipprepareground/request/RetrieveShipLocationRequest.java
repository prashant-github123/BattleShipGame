/**
 * 
 */
package com.battleshipprepareground.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author amall3
 *
 */
public class RetrieveShipLocationRequest {

	@NotBlank(message = "Game id is mandatory.")
	private String gameId;

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

}
