/**
 * 
 */
package com.battleshipplaygame.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import com.battleshipplaygame.request.CheckTurnStatusRequest;
import com.battleshipplaygame.response.CheckTurnStatusResponse;

/**
 * @author amall3
 *
 */
public interface PlayGameService {

	/**
	 * Method that returns the validation errors separated by comma.
	 * 
	 * @param errors
	 * @return
	 */
	StringBuilder getValidationErrors(Errors errors);

	ResponseEntity<CheckTurnStatusResponse> processGame(CheckTurnStatusRequest checkTurnStatusRequest);
}