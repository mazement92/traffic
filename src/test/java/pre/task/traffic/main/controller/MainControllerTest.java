package pre.task.traffic.main.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class MainControllerTest {
	@Autowired
    private MainController mainController;

    private MockMvc mockMvc;
    
    @Test
    public void main() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}