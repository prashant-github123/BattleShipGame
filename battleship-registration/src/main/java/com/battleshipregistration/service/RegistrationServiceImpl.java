/**
 * 
 */
package com.battleshipregistration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.battleshipregistration.constants.BattleShipConstants;
import com.battleshipregistration.domain.BattleShipGame;
import com.battleshipregistration.domain.Player;
import com.battleshipregistration.response.AddPlayerResponse;

/**
 * <p>
 * Service class for related to Player Registration.
 * </p>
 * 
 * @author amall3
 *
 */
@Service
@PropertySource("classpath:battleshipGameConfiguration.properties")
public class RegistrationServiceImpl implements RegistrationService {
	
	private static final String ADD_PLAYER_SUCCESS_MSG = "Added a new Player with name ";

	@Value("${battleship.initialGameId}")
	private String initialGameId;

	@Value("${battleship.firstPlayerId}")
	private String firstPlayerId;

	@Value("${battleship.secondPlayerId}")
	private String secondPlayerId;

	@Autowired
	public BattleShipGame game;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @param addPlayerRequest
	 * @param response
	 * @return
	 * @throws NumberFormatException
	 */
	public ResponseEntity<AddPlayerResponse> createNewPlayer(String playerName) {
		
		AddPlayerResponse response = new AddPlayerResponse();
		TreeMap<String, List<Player>> playersData = (TreeMap<String, List<Player>>) game.getPlayers();
		if (null == playersData) {
			
			Map<String, List<Player>> players = new HashMap<>();
			List<Player> playerList = new ArrayList<>();
			playerList.add(new Player(firstPlayerId, playerName, Boolean.TRUE));
			players.put(initialGameId, playerList);
			game.setPlayers(players);
			
			response.setGameId(initialGameId);
			response.setPlayerId(firstPlayerId);
			response.setPlayerName(playerName);
			response.setStatus(Boolean.TRUE);
			response.setMessage(ADD_PLAYER_SUCCESS_MSG + playerName);
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		}
		
		Entry<String, List<Player>> lastEntry = playersData.lastEntry();

			List<Player> playerList = lastEntry.getValue();
			if (!playerList.isEmpty() && playerList.size() < 2) {
				playerList.add(new Player(secondPlayerId , playerName, Boolean.TRUE));
				game.getPlayers().put(lastEntry.getKey(), playerList);
				
				response.setGameId(lastEntry.getKey());
				response.setPlayerId(secondPlayerId);
				response.setPlayerName(playerName);
				response.setStatus(Boolean.TRUE);
				response.setMessage(ADD_PLAYER_SUCCESS_MSG + playerName);
				response.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
				
			} else {
				String key = lastEntry.getKey();
				int parseInt = Integer.parseInt(key);
				String newKey = String.valueOf(parseInt + 1);

				List<Player> players = new ArrayList<>();
				players.add(new Player(firstPlayerId , playerName, Boolean.TRUE));
				game.getPlayers().put(newKey, players);
				
				response.setGameId(newKey);
				response.setPlayerId(firstPlayerId);
				response.setPlayerName(playerName);
				response.setStatus(Boolean.TRUE);
				response.setMessage(ADD_PLAYER_SUCCESS_MSG + playerName);
				response.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
			}
	}
	
	/**
	 * Method that iterates the validation errors and returns a comma separated
	 * error message.
	 * 
	 * @param errors
	 * @return
	 */
	public StringBuilder getValidationErrors(Errors errors) {

		logger.info("Inside BattleShipRegistrationController.getValidationErrors()");
		StringBuilder errorMessages = new StringBuilder();

		for (ObjectError objErr : errors.getAllErrors()) {
			if (!StringUtils.isEmpty(errorMessages))
				logger.debug("Error Message is : ", objErr.getDefaultMessage());
			errorMessages = errorMessages.append(objErr.getDefaultMessage()).append(BattleShipConstants.COMMA);
		}
		return errorMessages;
	}

}
