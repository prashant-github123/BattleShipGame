/**
 * 
 */
package com.battleshipprepareground.api;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
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

import com.battleshipprepareground.constants.BattleShipConstants;
import com.battleshipprepareground.domain.BattleShipGame;
import com.battleshipprepareground.domain.Player;
import com.battleshipprepareground.request.PlaceShipRequest;
import com.battleshipprepareground.request.RetrieveShipLocationRequest;
import com.battleshipprepareground.response.PlaceShipResponse;
import com.battleshipprepareground.response.RetrieveCoordinatePlayerData;
import com.battleshipprepareground.response.RetrieveShipLocationResponse;

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
public class PrepareGroundServiceImpl implements PrepareGroundService {
	
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
	
	
	/**
	 * @param placeShipRequest
	 * @param response
	 * @return 
	 */
	public ResponseEntity<PlaceShipResponse> populatePlayerShipCoordinate(PlaceShipRequest placeShipRequest, PlaceShipResponse response) {
		
		
		TreeMap<String, List<Player>> playersData = (TreeMap<String, List<Player>>) game.getPlayers();
		if (null == playersData) 
			return createNewGame(placeShipRequest, response);

		List<Player> players = playersData.get(placeShipRequest.getGameId());
		if(null == players)
			return createNewGame(placeShipRequest, response);
		
		boolean playerNotFound = Boolean.TRUE;
		for (Player pl : players) {
			if (placeShipRequest.getPlayerId().equalsIgnoreCase(pl.getId())) {
				
				playerNotFound = Boolean.FALSE;
				List<String> shipCoord = new ArrayList<String>();

				createCoordinateList(placeShipRequest, shipCoord);
				pl.setShipCoordinates(shipCoord);

				response.setStatus(Boolean.TRUE);
				response.setMessage("Coordinates Updated Successfully for User " + placeShipRequest.getPlayerId());
				response.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
				
			}
		}
		
		if(playerNotFound){
			List<String> shipCoord = new ArrayList<String>();
			createCoordinateList(placeShipRequest, shipCoord);
			players.add(new Player(placeShipRequest.getPlayerId(), placeShipRequest.getPlayerName(), Boolean.TRUE, shipCoord));
			
			response.setStatus(Boolean.TRUE);
			response.setMessage("Coordinates Updated Successfully for User " + placeShipRequest.getPlayerId());
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		}
		
		response.setStatus(Boolean.FALSE);
		response.setMessage("Could not find GAME / PLAYER to update the Coordinates.");
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * @param placeShipRequest
	 * @param response
	 * @return
	 */
	private ResponseEntity<PlaceShipResponse> createNewGame(PlaceShipRequest placeShipRequest, PlaceShipResponse response) {
		
		Map<String, List<Player>> players = new HashMap<String, List<Player>>();
		List<Player> playerList = new ArrayList<Player>();

		List<String> shipCoord = new ArrayList<String>();
		createCoordinateList(placeShipRequest, shipCoord);

		playerList.add(new Player(placeShipRequest.getPlayerId(), placeShipRequest.getPlayerName(), Boolean.TRUE, shipCoord));
		players.put(placeShipRequest.getGameId(), playerList);
		game.setPlayers(players);

		response.setStatus(Boolean.TRUE);
		response.setMessage("Coordinates Updated Successfully for User " + placeShipRequest.getPlayerId());
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}



	/**
	 * @param placeShipRequest
	 * @param shipCoord
	 */
	private void createCoordinateList(PlaceShipRequest placeShipRequest, List<String> shipCoord) {
		String[] coordinatesArray = StringUtils.split(placeShipRequest.getShipCoordinates(), BattleShipConstants.COMMA);
		for (String coordinate : coordinatesArray) {
			shipCoord.add(coordinate);
		}
	}
	
	
	/**
	 * @param retrieveShipLocationRequest
	 * @param response
	 * @return
	 */
	public ResponseEntity<RetrieveShipLocationResponse> fetchPlayerDetails(RetrieveShipLocationRequest retrieveShipLocationRequest) {
		
		RetrieveShipLocationResponse response = new RetrieveShipLocationResponse();
		try {
			TreeMap<String, List<Player>> playersData = (TreeMap<String, List<Player>>) game.getPlayers();
			if (null == playersData) {

				response.setStatus(Boolean.FALSE);
				response.setMessage("No Games Or Players Found To Return the Ship Coordinates.");
				response.setStatusCode(HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}

			List<Player> players = playersData.get(retrieveShipLocationRequest.getGameId());
			if (null != players && !players.isEmpty()) {

				List<RetrieveCoordinatePlayerData> playerList = new ArrayList<RetrieveCoordinatePlayerData>();
				if (null != players && players.size() > 1) {

					for (Player pl : players) {
							
						RetrieveCoordinatePlayerData pResponse = new RetrieveCoordinatePlayerData();
						BeanUtils.copyProperties(pResponse, pl);
						
						List<String> shipCoordinates = pl.getShipCoordinates();
						if (null != shipCoordinates && !shipCoordinates.isEmpty()) {
							pResponse.setReadyToPlay(Boolean.TRUE);
						} else {
							pResponse.setReadyToPlay(Boolean.FALSE);
						}
						playerList.add(pResponse);
					}
					
					response.setGameId(retrieveShipLocationRequest.getGameId());
					response.setProceedGame(Boolean.TRUE);
					response.setPlayers(playerList);
					response.setStatus(Boolean.TRUE);
					response.setMessage("Coordinates Returned Successfully for Game " + retrieveShipLocationRequest.getGameId());
					response.setStatusCode(HttpStatus.OK.value());
					return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
				} else {
					response.setProceedGame(Boolean.FALSE);
					response.setGameId(retrieveShipLocationRequest.getGameId());
					response.setStatus(Boolean.FALSE);
					response.setMessage("Only One Player Data is available. " + retrieveShipLocationRequest.getGameId());
					response.setStatusCode(HttpStatus.OK.value());
					return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
				}
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Excption while copying values.", e);
		}
		response.setStatus(Boolean.FALSE);
		response.setMessage("Could not find GAME / PLAYER to return the Coordinates.");
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	

}
