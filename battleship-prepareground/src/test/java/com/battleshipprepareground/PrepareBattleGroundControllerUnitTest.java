package com.battleshipprepareground;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.battleshipprepareground.controller.PrepareBattleGroundController;
import com.battleshipprepareground.domain.BattleShipGame;
import com.battleshipprepareground.service.PrepareGroundService;



@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipPreparegroundApplication.class})
public class PrepareBattleGroundControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private BattleShipGame game;
    
    @Mock
    private PrepareGroundService service;

    @InjectMocks
    private PrepareBattleGroundController prepareController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(prepareController)
                .build();
    }

    
    /**
     * This method is to unit test place a ship service
     * 
     * @throws Exception
     */
    @Test
    public void testPlaceShipService() throws Exception {
        
    		//Place a Ship for player
    		mockMvc.perform(
			        post("/rest/api/placeShip")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{\"gameId\":\"101\","
			        		+ "\"playerId\":\"p1\","
			        		+ "\"playerName\":\"Prashant\","
			        		+ "\"shipCoordinates\":\"10,11,12\""
			        		+ "}"))			                
				.andExpect(status().isOk());
       
    			 	
    }

    
    /**
     * This method is to unit test retrieve Ship Locations from players of the game
     * 
     * @throws Exception
     */
    @Test
    public void testRetrieveShipLocationsService() throws Exception {
        
    		//Place a Ship for player
    		mockMvc.perform(
			        post("/rest/api/retrieveShipLocations")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{\"gameId\":\"101\"}"))			                
				.andExpect(status().isOk());
       
    			 	
    }

    
}
