/**
 * 
 */
package com.battleshipprepareground.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import com.battleshipprepareground.request.PlaceShipRequest;
import com.battleshipprepareground.request.RetrieveShipLocationRequest;
import com.battleshipprepareground.response.PlaceShipResponse;
import com.battleshipprepareground.response.RetrieveShipLocationResponse;

/**
 * @author amall3
 *
 */
public interface PrepareGroundService {

	/**
	 * Method that returns the validation errors separated by comma.
	 * 
	 * @param errors
	 * @return
	 */
	StringBuilder getValidationErrors(Errors errors);

	/**
	 * Method responsible for creating new Game/ new Player and populate the
	 * player's ship coordinates.
	 * 
	 * @param placeShipRequest
	 * @param response
	 * @return
	 */
	ResponseEntity<PlaceShipResponse> populatePlayerShipCoordinate(PlaceShipRequest placeShipRequest, PlaceShipResponse response);

	/**
	 * Method that retrieves the Player's data for the game requested.
	 * 
	 * @param retrieveShipLocationRequest
	 * @return
	 */
	ResponseEntity<RetrieveShipLocationResponse> fetchPlayerDetails(RetrieveShipLocationRequest retrieveShipLocationRequest);

}