/**
 * 
 */
package com.battleshipplaygame.response;

import java.util.List;

/**
 * @author amall3
 *
 */
public class CheckTurnStatusResponse extends Response {

	private boolean turn;

	private String playerId;

	private String gameId;

	private List<String> opponentHitCoordinates;

	private boolean gameOver;


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
	 * @return the turn
	 */
	public boolean isTurn() {
		return turn;
	}

	/**
	 * @param turn
	 *            the turn to set
	 */
	public void setTurn(boolean turn) {
		this.turn = turn;
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
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return the opponentHitCoordinates
	 */
	public List<String> getOpponentHitCoordinates() {
		return opponentHitCoordinates;
	}

	/**
	 * @param opponentHitCoordinates the opponentHitCoordinates to set
	 */
	public void setOpponentHitCoordinates(List<String> opponentHitCoordinates) {
		this.opponentHitCoordinates = opponentHitCoordinates;
	}

}
