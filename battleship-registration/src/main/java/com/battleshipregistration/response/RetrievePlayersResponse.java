/**
 * 
 */
package com.battleshipregistration.response;

import java.util.List;

/**
 * @author amall3
 *
 */
public class RetrievePlayersResponse extends Response {

	private List<PlayerResponse> players;

	/**
	 * @return the players
	 */
	public List<PlayerResponse> getPlayers() {
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(List<PlayerResponse> players) {
		this.players = players;
	}

}
