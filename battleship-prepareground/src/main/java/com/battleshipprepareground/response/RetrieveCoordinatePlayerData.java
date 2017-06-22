/**
 * 
 */
package com.battleshipprepareground.response;

import java.util.List;

/**
 * @author amall3
 *
 */
public class RetrieveCoordinatePlayerData {

	private String id;

	private String name;

	private List<String> shipCoordinates;

	private boolean readyToPlay;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
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
	 * @return the readyToPlay
	 */
	public boolean isReadyToPlay() {
		return readyToPlay;
	}

	/**
	 * @param readyToPlay
	 *            the readyToPlay to set
	 */
	public void setReadyToPlay(boolean readyToPlay) {
		this.readyToPlay = readyToPlay;
	}

}
