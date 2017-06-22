package com.battleshipregistration.controller;

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

import com.battleshipregistration.api.RegistrationService;
import com.battleshipregistration.domain.BattleShipGame;
import com.battleshipregistration.request.AddPlayerRequest;
import com.battleshipregistration.response.AddPlayerResponse;

@RestController
@RequestMapping("/rest/api")
@CrossOrigin
public class BattleShipRegistrationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public BattleShipGame game;
	
	@Autowired
	private RegistrationService registrationService;


	/**
	 * Method responsible for adding players in a game. If the players in a game
	 * exceeds the configurable value (number of players) , It starts a new
	 * game.
	 * 
	 * @param addPlayerRequest
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/addPlayers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddPlayerResponse> registerPlayer(@Valid @RequestBody AddPlayerRequest addPlayerRequest, Errors errors) {

		logger.info("Inside BattleShipRegistrationController.registerPlayer()");
		AddPlayerResponse response = new AddPlayerResponse();
		
		// If error, just return a 400 bad request, along with the error message.
		if (errors.hasErrors()) {
			response.setMessage(registrationService.getValidationErrors(errors).toString());
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(response);
		}

		return registrationService.createNewPlayer(addPlayerRequest, response);
	}

}
