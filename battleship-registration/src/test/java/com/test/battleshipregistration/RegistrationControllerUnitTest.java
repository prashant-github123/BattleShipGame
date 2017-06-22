package com.test.battleshipregistration;

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

import com.battleshipregistration.BattleshipRegistrationApplication;
import com.battleshipregistration.controller.BattleShipRegistrationController;
import com.battleshipregistration.domain.BattleShipGame;
import com.battleshipregistration.service.RegistrationServiceImpl;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipRegistrationApplication.class})
public class RegistrationControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private BattleShipGame game;
    
    @Mock
    private RegistrationServiceImpl service;

    @InjectMocks
    private BattleShipRegistrationController registrationController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(registrationController)
                .build();
    }

    // =========================================== Add Player ==========================================

    @Test
    public void testAddPlayerService() throws Exception {
        
    		//Add 1st Player
    		mockMvc.perform(
			        post("/rest/api/addPlayers")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{\"playerName\":\"Prashant\"}"))			                
				.andExpect(status().isOk());
    		
    		//Add 2nd Player
    		mockMvc.perform(
			        post("/rest/api/addPlayers")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{\"playerName\":\"Abhishek\"}"))			                
				.andExpect(status().isOk());
    		
    }

    
}
