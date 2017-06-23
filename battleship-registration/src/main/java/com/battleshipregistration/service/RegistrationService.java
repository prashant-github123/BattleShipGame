/**
 * 
 */
package com.battleshipregistration.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

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
	ResponseEntity<AddPlayerResponse> createNewPlayer(String addPlayerRequest);
	
	/**
	 * 
	 * @param errors
	 * @return
	 */
	StringBuilder getValidationErrors(Errors errors);
}