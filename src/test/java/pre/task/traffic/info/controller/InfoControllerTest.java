package pre.task.traffic.info.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class InfoControllerTest {
	@Autowired
    private InfoController infoController;

    @Autowired
    private ObjectMapper objectMapper;
    
    private MockMvc mockMvc;
    
    @Test
    public void infoMain() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(infoController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/info/infoMain"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void insertTrafficData() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(infoController).build();
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("dataPath", "/data/seoul.csv");
        String content = objectMapper.writeValueAsString(map);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/info/insertTrafficData").contentType(MediaType.APPLICATION_JSON).content(content))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void ajaxDailyAvg() throws Exception {
    	mockMvc = MockMvcBuilders.standaloneSetup(infoController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/info/ajaxDailyAvg"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void ajaxMonthlyAvg() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(infoController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/info/ajaxMonthlyAvg"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void ajaxMaxDiff() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(infoController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/info/ajaxMaxDiff"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}