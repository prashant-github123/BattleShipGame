/**
 * 
 */
package com.battleshipplaygame.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

/**
 * @author amall3
 *
 */
@Component
public class BattleShipGame {

	private Map<String, String> whoseTurnMap;

	private Map<String, Boolean> isGameOverMap;

	private NavigableMap<String, List<Player>> players;

	public Map<String, List<Player>> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, List<Player>> players) {
		this.players = new TreeMap<String, List<Player>>(players);
	}

	/**
	 * @return the whoseTurnMap
	 */
	public Map<String, String> getWhoseTurnMap() {
		if (null == whoseTurnMap)
			whoseTurnMap =  new HashMap<String, String>();
		
		return whoseTurnMap;
		
	}

	/**
	 * @param whoseTurnMap
	 *            the whoseTurnMap to set
	 */
	public void setWhoseTurnMap(Map<String, String> whoseTurnMap) {
		this.whoseTurnMap = whoseTurnMap;
	}

	/**
	 * @return the isGameOverMap
	 */
	public Map<String, Boolean> getIsGameOverMap() {
		if (null == isGameOverMap) 
			isGameOverMap = new HashMap<String, Boolean>();
		 
			return isGameOverMap;
		}
	

	/**
	 * @param isGameOverMap
	 *            the isGameOverMap to set
	 */
	public void setIsGameOverMap(Map<String, Boolean> isGameOverMap) {
		this.isGameOverMap = isGameOverMap;
	}

}
