/**
 * 
 */
package com.battleshipprepareground.domain;

import java.util.List;

/**
 * @author amall3
 *
 */
public class Player extends BaseEntity {

	public Player(String id, String name, boolean isSelected, List<String> shipCoordinates) {
		super();
		this.id = id;
		this.name = name;
		this.isSelected = isSelected;
		this.shipCoordinates = shipCoordinates;
	}

	private String name;

	private boolean isSelected;

	private List<String> shipCoordinates;

	public Player(String id, String name, boolean isSelected) {
		super();
		this.id = id;
		this.name = name;
		this.isSelected = isSelected;
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
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected
	 *            the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
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

}
