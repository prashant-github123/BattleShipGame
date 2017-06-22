/**
 * 
 */
package com.battleshipplaygame.domain;

import java.util.List;

/**
 * @author amall3
 *
 */
public class Player extends BaseEntity {

	private String name;

	private List<String> shipCoordinates;

	private List<String> opponentHitCoordinates;

	public Player(String id, String name, boolean isSelected) {
		super();
		this.id = id;
		this.name = name;
	}

	public Player(String id, String name, boolean isSelected, List<String> opponentHitCoordinates) {
		super();
		this.id = id;
		this.name = name;
		this.opponentHitCoordinates = opponentHitCoordinates;
	}

	public Player(String id) {
		super();
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shipCoordinates
	 */
	public List<String> getShipCoordinates() {
		return shipCoordinates;
	}

	/**
	 * @param shipCoordinates
	 *            the shipCoordinates to set
	 */
	public void setShipCoordinates(List<String> shipCoordinates) {
		this.shipCoordinates = shipCoordinates;
	}

	/**
	 * @return the opponentHitCoordinates
	 */
	public List<String> getOpponentHitCoordinates() {
		return opponentHitCoordinates;
	}

	/**
	 * @param opponentHitCoordinates
	 *            the opponentHitCoordinates to set
	 */
	public void setOpponentHitCoordinates(List<String> opponentHitCoordinates) {
		this.opponentHitCoordinates = opponentHitCoordinates;
	}

}
