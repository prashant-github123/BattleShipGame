package com.battleshipplaygame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.battleshipplaygame.domain.BattleShipGame;
import com.battleshipplaygame.domain.Player;
import com.battleshipplaygame.request.CheckTurnStatusRequest;
import com.battleshipplaygame.response.CheckTurnStatusResponse;
import com.battleshipplaygame.service.PlayGameService;

@RestController
@RequestMapping("/rest/api")
@PropertySource("classpath:battleshipGameConfiguration.properties")
@CrossOrigin
public class PlayBattleShipGameController {

	private static final String P2 = "p2";

	private static final String P1 = "p1";

	private static final String MISS = "miss";

	private static final String HIT = "hit";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${battleship.scoreReward}")
	private int scoreReward;

	@Autowired
	public BattleShipGame game;

	@Autowired
	private PlayGameService playGameService;
	
	
	/**
	 * Method responsible for returning the turn status for the passed in game
	 * Id.
	 * 
	 * @param gameId
	 * @return
	 */
	@RequestMapping(value = "/checkTurnStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CheckTurnStatusResponse> checkTurnStatus(@Valid @RequestBody CheckTurnStatusRequest checkTurnStatusRequest, Errors errors) {

		logger.info("Inside PlayBattleShipGameController.checkTurnStatus()");
		CheckTurnStatusResponse response = new CheckTurnStatusResponse();
		
		// If error, just return a 400 bad request, along with the error message.
		if (errors.hasErrors()) {
			response.setMessage(playGameService.getValidationErrors(errors).toString());
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(response);
		}

		logger.debug("The Game Id Passed Is {0}:", checkTurnStatusRequest.getGameId());
		logger.debug("The Player Id Passed Is {0}:", checkTurnStatusRequest.getPlayerId());
		logger.debug("The Player Name Passed Is {0}:", checkTurnStatusRequest.getPlayerName());
		
		TreeMap<String, List<Player>> playersData = (TreeMap<String, List<Player>>) game.getPlayers();
		if (null == playersData)
			createNewGame(checkTurnStatusRequest);
		
		playersData = (TreeMap<String, List<Player>>) game.getPlayers();

		List<Player> players = playersData.get(checkTurnStatusRequest.getGameId());
		if (null == players)
			createNewGame(checkTurnStatusRequest);
		
		players = game.getPlayers().get(checkTurnStatusRequest.getGameId());

		for (Player pl : players) {
			if (checkTurnStatusRequest.getPlayerId().equalsIgnoreCase(pl.getId()) && StringUtils.isEmpty(pl.getName())) {
				pl.setName(checkTurnStatusRequest.getPlayerName());
			}
		}

		if (checkTurnStatusRequest.isCheckTurnStatusOnly()) {

			if (game.isGameOver()) {
				response.setTurn(Boolean.FALSE);
				response.setGameOver(Boolean.TRUE);
				// return;
			} else {
				if (checkTurnStatusRequest.getPlayerId().equalsIgnoreCase(game.getWhoseTurn())) {
					response.setTurn(Boolean.TRUE);
					// return
				} else {
					response.setTurn(Boolean.FALSE);
					// return
				}
			}
		} else {
			if (checkTurnStatusRequest.isGameOver()) {
				game.setGameOver(Boolean.TRUE);
				// return;
			} else if (HIT.equalsIgnoreCase(checkTurnStatusRequest.getHitOrMiss()) || MISS.equalsIgnoreCase(checkTurnStatusRequest.getHitOrMiss())) {
				
				
				for (Player pl : playersData.get(checkTurnStatusRequest.getGameId())) {
					
					if (!checkTurnStatusRequest.getPlayerId().equalsIgnoreCase(pl.getId())) {
					
						List<String> hitCoodinate = pl.getOpponentHitCoordinates();
						if (null == hitCoodinate) {
							hitCoodinate = new ArrayList<String>();
						}
						hitCoodinate.add(checkTurnStatusRequest.getHitCoordinate());
						pl.setOpponentHitCoordinates(hitCoodinate);
					}
				}

				if (P1.equalsIgnoreCase(checkTurnStatusRequest.getPlayerId())) {
					game.setWhoseTurn(P2);
				} else {
					game.setWhoseTurn(P1);
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


	/**
	 * 
	 * @param checkTurnStatusRequest
	 * @return
	 */
	private void createNewGame(CheckTurnStatusRequest checkTurnStatusRequest) {

		logger.info("Inside PlayBattleShipGameController.createNewGame()");

		Map<String, List<Player>> players = new TreeMap<String, List<Player>>();
		List<Player> playerList = new ArrayList<Player>();

		playerList.add(new Player("p1"));
		playerList.add(new Player("p2"));
		players.put(checkTurnStatusRequest.getGameId(), playerList);
		game.setPlayers(players);
	}

}
