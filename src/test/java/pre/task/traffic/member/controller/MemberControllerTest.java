package pre.task.traffic.member.controller;

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

import pre.task.traffic.member.dto.MemberDto;

@SpringBootTest
class MemberControllerTest {
	@Autowired
    private MemberController memberController;

    @Autowired
    private ObjectMapper objectMapper;
    
    private MockMvc mockMvc;
    
    @Test
    public void insertMember() throws Exception {
    	mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

    	MemberDto memberDto = new MemberDto();
    	memberDto.setMemberId("admin");
    	memberDto.setMemberPwd("123");
        String content = objectMapper.writeValueAsString(memberDto);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/member/insertMember").contentType(MediaType.APPLICATION_JSON).content(content))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));;
	}
    
    @Test
    public void login() throws Exception {
    	mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    	MemberDto memberDto = new MemberDto();
    	memberDto.setMemberId("tester");
    	memberDto.setMemberPwd("123");
        String content = objectMapper.writeValueAsString(memberDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/member/login").contentType(MediaType.APPLICATION_JSON).content(content))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));;
	}
    
	@Test
	public void logout() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

		mockMvc.perform(MockMvcRequestBuilders.post("/member/logout").contentType(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));;
	}
}