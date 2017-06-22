package com.battleshipprepareground.controller;

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

import com.battleshipprepareground.request.PlaceShipRequest;
import com.battleshipprepareground.request.RetrieveShipLocationRequest;
import com.battleshipprepareground.response.PlaceShipResponse;
import com.battleshipprepareground.response.RetrieveShipLocationResponse;
import com.battleshipprepareground.service.PrepareGroundService;

@RestController
@RequestMapping("/rest/api")
@CrossOrigin
public class PrepareBattleGroundController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public PrepareGroundService prepareGroundService;

	/**
	 * <p>
	 * Method responsible for adding the ship coordinates to a player.
	 * </p>
	 * 
	 * @param placeShipRequest
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/placeShip", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlaceShipResponse> placeShip(@Valid @RequestBody PlaceShipRequest placeShipRequest, Errors errors) {

		logger.debug("Inside PrepareBattleGroundController.placeShip()");
		PlaceShipResponse response = new PlaceShipResponse();

		// If error, just return a 400 bad request, along with the error message.
		if (errors.hasErrors()) {
			response.setMessage(prepareGroundService.getValidationErrors(errors).toString());
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(response);
		}

		logger.debug("The Game Id Passed Is {0}:", placeShipRequest.getGameId());
		logger.debug("The Player Id Passed Is {0}:", placeShipRequest.getPlayerId());
		logger.debug("The Coordinates Passed Is {0}:", placeShipRequest.getShipCoordinates());
		
		return prepareGroundService.populatePlayerShipCoordinate(placeShipRequest, response);
	}

	/**
	 * <p>
	 * Method responsible for Retrieving the ship coordinates for both the
	 * players involved in the game.
	 * </p>
	 * 
	 * @param placeShipRequest
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/retrieveShipLocations", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RetrieveShipLocationResponse> retrieveShipLocations(@Valid @RequestBody RetrieveShipLocationRequest retrieveShipLocationRequest, Errors errors) {

		logger.debug("Inside PrepareBattleGroundController.placeShip()");
		
		// If error, just return a 400 bad request, along with the error
		// message.
		if (errors.hasErrors()) {
			RetrieveShipLocationResponse response = new RetrieveShipLocationResponse();
			response.setMessage(prepareGroundService.getValidationErrors(errors).toString());
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(response);
		}

		logger.debug("The Game Id Passed Is {0}:", retrieveShipLocationRequest.getGameId());
		return prepareGroundService.fetchPlayerDetails(retrieveShipLocationRequest);
	}

}
