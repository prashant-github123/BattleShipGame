package com.test.battleshipplaygame;

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

import com.battleshipplaygame.BattleshipPlaygameApplication;
import com.battleshipplaygame.controller.PlayBattleShipGameController;
import com.battleshipplaygame.domain.BattleShipGame;
import com.battleshipplaygame.service.PlayGameService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipPlaygameApplication.class})
public class PlayBattleshipGameControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private BattleShipGame game;
    
    @Mock
    private PlayGameService playGameService;
    
    @InjectMocks
    private PlayBattleShipGameController playgameController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(playgameController)
                .build();
    }

    @Test
    public void testCheckTurnP1UnitService() throws Exception {
        
    		
    	
    		//Check service when Player hit the enemy ship.
    		mockMvc.perform(
			        post("/rest/api/checkTurnStatus")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{"
								+ "\"gameId\": \"111\","
								+ "\"playerId\": \"p1\","
								+ "\"playerName\": \"Prashant\","
								+ "\"hitOrMiss\":\"hit\","
								+ "\"gameOver\": false,"
								+ "\"checkTurnStatusOnly\":false,"
								+ "\"hitCoordinate\":\"20\""
								+ "}"))			                
				.andExpect(status().isOk());
    		
    }
    
    @Test
    public void testCheckTurnP2UnitService() throws Exception {
        
    		//Check service when Player check for his turn
    		mockMvc.perform(
			        post("/rest/api/checkTurnStatus")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{"
								+ "\"gameId\": \"111\","
								+ "\"playerId\": \"p2\","
								+ "\"playerName\": \"Abhishek\","
								+ "\"hitOrMiss\":\"\","
								+ "\"gameOver\": false,"
								+ "\"checkTurnStatusOnly\":true,"
								+ "\"hitCoordinate\":\"\""
								+ "}"))			                
				.andExpect(status().isOk());
    		
    }

    
}
