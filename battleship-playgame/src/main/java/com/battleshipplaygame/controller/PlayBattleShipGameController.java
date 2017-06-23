package com.battleshipplaygame.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.battleshipplaygame.request.CheckTurnStatusRequest;
import com.battleshipplaygame.response.CheckTurnStatusResponse;
import com.battleshipplaygame.service.PlayGameService;

@RestController
@RequestMapping("/rest/api")
@CrossOrigin
public class PlayBattleShipGameController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
		
		// If error, just return a 400 bad request, along with the error message.
		if (errors.hasErrors()) {
			CheckTurnStatusResponse response = new CheckTurnStatusResponse();
			response.setMessage(playGameService.getValidationErrors(errors).toString());
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(response);
		}

		logger.debug("The Game Id Passed Is {0}:", checkTurnStatusRequest.getGameId());
		logger.debug("The Player Id Passed Is {0}:", checkTurnStatusRequest.getPlayerId());
		logger.debug("The Player Name Passed Is {0}:", checkTurnStatusRequest.getPlayerName());
		
		return playGameService.processGame(checkTurnStatusRequest);
	}
}
