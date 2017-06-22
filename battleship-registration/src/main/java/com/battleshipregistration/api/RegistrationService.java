/**
 * 
 */
package com.battleshipregistration.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import com.battleshipregistration.request.AddPlayerRequest;
import com.battleshipregistration.response.AddPlayerResponse;

/**
 * @author amall3
 *
 */
public interface RegistrationService {

	/**
	 * 
	 * @param addPlayerRequest
	 * @param response
	 * @return
	 * @throws NumberFormatException
	 */
	ResponseEntity<AddPlayerResponse> createNewPlayer(AddPlayerRequest addPlayerRequest, AddPlayerResponse response)
			throws NumberFormatException;
	
	/**
	 * 
	 * @param errors
	 * @return
	 */
	StringBuilder getValidationErrors(Errors errors);
}