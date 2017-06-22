/**
 * 
 */
package com.battleshipregistration.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

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
import com.battleshipregistration.request.AddPlayerRequest;
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
	public ResponseEntity<AddPlayerResponse> createNewPlayer(AddPlayerRequest addPlayerRequest, AddPlayerResponse response) throws NumberFormatException {
		
		TreeMap<String, List<Player>> playersData = (TreeMap<String, List<Player>>) game.getPlayers();
		if (null == playersData) {
			
			Map<String, List<Player>> players = new HashMap<String, List<Player>>();
			List<Player> playerList = new ArrayList<Player>();
			playerList.add(new Player(firstPlayerId, addPlayerRequest.getPlayerName(), Boolean.TRUE));
			players.put(initialGameId, playerList);
			game.setPlayers(players);
			
			response.setGameId(initialGameId);
			response.setPlayerId(firstPlayerId);
			response.setStatus(Boolean.TRUE);
			response.setMessage("Added a new Player with name " + addPlayerRequest.getPlayerName());
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		}
		
		Entry<String, List<Player>> lastEntry = playersData.lastEntry();

			List<Player> playerList = lastEntry.getValue();
			if (!playerList.isEmpty() && playerList.size() < 2) {
				playerList.add(new Player(secondPlayerId , addPlayerRequest.getPlayerName(), Boolean.TRUE));
				game.getPlayers().put(lastEntry.getKey(), playerList);
				
				response.setGameId(lastEntry.getKey());
				response.setPlayerId(secondPlayerId);
				response.setStatus(Boolean.TRUE);
				response.setMessage("Added a new Player with name " + addPlayerRequest.getPlayerName());
				response.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
				
			} else {
				String key = lastEntry.getKey();
				int parseInt = Integer.parseInt(key);
				String newKey = String.valueOf(parseInt + 1);

				List<Player> players = new ArrayList<Player>();
				players.add(new Player(firstPlayerId , addPlayerRequest.getPlayerName(), Boolean.TRUE));
				game.getPlayers().put(newKey, players);
				
				response.setGameId(newKey);
				response.setPlayerId(firstPlayerId);
				response.setStatus(Boolean.TRUE);
				response.setMessage("Added a new Player with name " + addPlayerRequest.getPlayerName());
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
