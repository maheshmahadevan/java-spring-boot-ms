package com.example.msdemo;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.NONE,
  classes = MsDemoApplication.class)
@AutoConfigureMockMvc
public class BookSpringBootTest {
	
	@Autowired
    private MockMvc mvc;
 
    @Autowired
    private BookRepository repository;
 

    @Test
    public void givenBooks_whenGetBooks_thenStatus200()
      throws Exception {
     
        
     
       mvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk());
          
    }
    
}
