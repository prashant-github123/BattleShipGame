/**
 * 
 */
package com.battleshipplaygame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.battleshipplaygame.constants.BattleShipConstants;
import com.battleshipplaygame.domain.BattleShipGame;
import com.battleshipplaygame.domain.Player;
import com.battleshipplaygame.request.CheckTurnStatusRequest;
import com.battleshipplaygame.response.CheckTurnStatusResponse;

/**
 * <p>
 * Service class for related to Player Registration.
 * </p>
 * 
 * @author amall3
 *
 */
@Service
public class PlayGameServiceImpl implements PlayGameService {

	@Autowired
	public BattleShipGame game;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * @param checkTurnStatusRequest
	 * @return
	 */
	private void createNewGame(CheckTurnStatusRequest checkTurnStatusRequest) {

		logger.info("Inside PlayGameServiceImpl.createNewGame()");

		Map<String, List<Player>> players = new TreeMap<>();
		List<Player> playerList = new ArrayList<>();

		playerList.add(new Player(BattleShipConstants.P1));
		playerList.add(new Player(BattleShipConstants.P2));
		players.put(checkTurnStatusRequest.getGameId(), playerList);
		game.setPlayers(players);
	}


	/**
	 * Method that iterates the validation errors and returns a comma separated
	 * error message.
	 * 
	 * @param errors
	 * @return
	 */
	public StringBuilder getValidationErrors(Errors errors) {

		logger.info("Inside PlayGameServiceImpl.getValidationErrors()");
		StringBuilder errorMessages = new StringBuilder();

		for (ObjectError objErr : errors.getAllErrors()) {
			if (!StringUtils.isEmpty(errorMessages))
				logger.debug("Error Message is : ", objErr.getDefaultMessage());
			errorMessages = errorMessages.append(objErr.getDefaultMessage())
					.append(BattleShipConstants.COMMA);
		}
		return errorMessages;
	}

	/**
	 * @param checkTurnStatusRequest
	 * @param response
	 * @return
	 */
	public ResponseEntity<CheckTurnStatusResponse> processGame(CheckTurnStatusRequest checkTurnStatusRequest) {
		
		logger.info("Inside PlayGameServiceImpl.processGame()");
		CheckTurnStatusResponse response = new CheckTurnStatusResponse();
		TreeMap<String, List<Player>> playersData = (TreeMap<String, List<Player>>) game.getPlayers();
		if (null == playersData)
			createNewGame(checkTurnStatusRequest);
		
		playersData = (TreeMap<String, List<Player>>) game.getPlayers();

		List<Player> players = playersData.get(checkTurnStatusRequest.getGameId());
		if (null == players || players.isEmpty()){
			//createNewGame(checkTurnStatusRequest);
			List<Player> playerList = new ArrayList<>();

			playerList.add(new Player(BattleShipConstants.P1));
			playerList.add(new Player(BattleShipConstants.P2));
			playersData.put(checkTurnStatusRequest.getGameId(), playerList);

		}
		players = game.getPlayers().get(checkTurnStatusRequest.getGameId());

		for (Player pl : players) {
			if (checkTurnStatusRequest.getPlayerId().equalsIgnoreCase(pl.getId()) && StringUtils.isEmpty(pl.getName())) {
				pl.setName(checkTurnStatusRequest.getPlayerName());
			}
		}

		if (checkTurnStatusRequest.isCheckTurnStatusOnly()) {
			
			
			Boolean isGameOver = game.getIsGameOverMap().get(checkTurnStatusRequest.getGameId());
			
			if(null== isGameOver)
				isGameOver = Boolean.FALSE;

			if (isGameOver) {
				response.setTurn(Boolean.FALSE);
				response.setGameOver(Boolean.TRUE);
				// return;
			} else {
				
				String whoseTurn =  game.getWhoseTurnMap().get(checkTurnStatusRequest.getGameId());
				
				if (checkTurnStatusRequest.getPlayerId().equalsIgnoreCase(whoseTurn)) {
					response.setTurn(Boolean.TRUE);
					// return
				} else {
					response.setTurn(Boolean.FALSE);
					// return
				}
			}
		} else {
			if (checkTurnStatusRequest.isGameOver()) {
				//game.setGameOver(Boolean.TRUE);
				game.getIsGameOverMap().put(checkTurnStatusRequest.getGameId(), Boolean.TRUE);
				// return;
			} else if (BattleShipConstants.HIT.equalsIgnoreCase(checkTurnStatusRequest.getHitOrMiss()) || BattleShipConstants.MISS.equalsIgnoreCase(checkTurnStatusRequest.getHitOrMiss())) {
				
				
				for (Player pl : playersData.get(checkTurnStatusRequest.getGameId())) {
					
					if (!checkTurnStatusRequest.getPlayerId().equalsIgnoreCase(pl.getId())) {
					
						List<String> hitCoodinate = pl.getOpponentHitCoordinates();
						if (null == hitCoodinate) {
							hitCoodinate = new ArrayList<>();
						}
						hitCoodinate.add(checkTurnStatusRequest.getHitCoordinate());
						pl.setOpponentHitCoordinates(hitCoodinate);
					}
				}

				if (BattleShipConstants.P1.equalsIgnoreCase(checkTurnStatusRequest.getPlayerId())) {
					//game.setWhoseTurn(BattleShipConstants.P2);
					game.getWhoseTurnMap().put(checkTurnStatusRequest.getGameId(), BattleShipConstants.P2);
				} else {
					//game.setWhoseTurn(BattleShipConstants.P1);
					game.getWhoseTurnMap().put(checkTurnStatusRequest.getGameId(), BattleShipConstants.P1);
				}
			}
		}

		for (Player pl : playersData.get(checkTurnStatusRequest.getGameId())) {
			if (checkTurnStatusRequest.getPlayerId().equalsIgnoreCase(pl.getId())) {
				response.setOpponentHitCoordinates(pl.getOpponentHitCoordinates());
			}
		}
		
		response.setPlayerId(checkTurnStatusRequest.getPlayerId());
		response.setGameId(checkTurnStatusRequest.getGameId());
		response.setStatus(Boolean.TRUE);
		response.setMessage("Successfully Returned the Player Turn.");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}

	
}
