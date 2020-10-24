package pre.task.traffic.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pre.task.traffic.member.dto.MemberDto;

@SpringBootTest
class TokenServiceTest {
	@Autowired
	private TokenService tokenService;
	
	private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIifQ.HVr5yBJRgHhfoSc1YAwxZbAvOHPjA1nTfHctwFmUq7A";
    
    @Test
    void createToken() throws Exception {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("tester");
        memberDto.setMemberPwd("123");
        String testToken = "";
        testToken = tokenService.createToken(memberDto, "123");
    	assertThat(testToken).isEqualTo(token);
    }
    
    @Test
    void validateToken() {
    	boolean result = tokenService.validateToken(token);
    	assertThat(result).isEqualTo(true);
    }
}