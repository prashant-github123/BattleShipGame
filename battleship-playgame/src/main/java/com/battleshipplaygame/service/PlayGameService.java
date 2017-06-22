/**
 * 
 */
package com.battleshipplaygame.service;

import org.springframework.validation.Errors;

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


}