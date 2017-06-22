/**
 * 
 */
package com.battleshipplaygame.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.battleshipplaygame.constants.BattleShipConstants;
import com.battleshipplaygame.domain.BattleShipGame;

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
public class PlayGameServiceImpl implements PlayGameService {

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
			errorMessages = errorMessages.append(objErr.getDefaultMessage())
					.append(BattleShipConstants.COMMA);
		}
		return errorMessages;
	}

}
