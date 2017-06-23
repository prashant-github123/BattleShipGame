package com.test.battleshipregistration;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.battleshipregistration.BattleshipRegistrationApplication;
import com.battleshipregistration.controller.BattleShipRegistrationController;
import com.battleshipregistration.domain.BattleShipGame;
import com.battleshipregistration.response.AddPlayerResponse;
import com.battleshipregistration.service.RegistrationService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipRegistrationApplication.class})
public class RegistrationControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private BattleShipGame game;
    
    @Mock
    private RegistrationService service;

    @InjectMocks
    private BattleShipRegistrationController registrationController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(registrationController)
                .build();
    }

    
    @Test
    public void testAddPlayerService() throws Exception {
        	
	    	String addPlayerRequest = "John";
			AddPlayerResponse response = new AddPlayerResponse();
			response.setPlayerName("John");
			ResponseEntity<AddPlayerResponse> responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
			
			when(service.createNewPlayer(addPlayerRequest)).thenReturn(responseEntity);
			
    		//Add 1st Player
    		mockMvc.perform(
			        post("/rest/api/addPlayers")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{\"playerName\":\"John\"}"))			                
				.andExpect(status().isOk());
    		
    		verify(service).createNewPlayer(addPlayerRequest);
    }

    @Test
    public void testAddPlayerValidationService() throws Exception {
        
    		//For validation unit test with wrong parameter.
    		mockMvc.perform(
			        post("/rest/api/addPlayers")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        )			                
				.andExpect(status().isBadRequest());
    		
   		
    }

    
}
