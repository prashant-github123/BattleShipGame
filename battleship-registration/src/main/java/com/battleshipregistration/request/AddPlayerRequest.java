/**
 * 
 */
package com.battleshipregistration.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author amall3
 *
 */
public class AddPlayerRequest {

	@NotBlank(message = "Player Name is mandatory.")
	private String playerName;

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
