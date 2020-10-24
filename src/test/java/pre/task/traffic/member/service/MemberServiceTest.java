package pre.task.traffic.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pre.task.traffic.member.dto.MemberDto;

@SpringBootTest
@MybatisTest
class MemberServiceTest {
	@Autowired
	private MemberService memberService;
	
	private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIifQ.HVr5yBJRgHhfoSc1YAwxZbAvOHPjA1nTfHctwFmUq7A";
	
	@Test
    void insertMember() throws Exception {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId("testMember");
		memberDto.setMemberPwd("123");
		memberService.insertMember(memberDto);
    }
    
    @Test
    void getMemberInfo() throws Exception {
    	MemberDto memberDto = new MemberDto();
		memberDto.setMemberId("tester");
		memberDto.setMemberPwd("123");
		String testToken = memberService.getMemberInfo(memberDto);
		assertThat(testToken).isEqualTo(token);
    }
    
    @Test
    void getEncryptMemberPwd() throws Exception {
        StringBuffer sb = new StringBuffer();
		MessageDigest messageDigest;
		String testPwd = "";
		messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] passBytes = "123".getBytes();
	    messageDigest.reset();
	    byte[] digested = messageDigest.digest(passBytes);
	    for(int i = 0; i < digested.length; i++) {
	    	sb.append(Integer.toString((digested[i]&0xff) + 0x100, 16)
	    			.substring(1));
	    }
	    testPwd = memberService.getEncryptMemberPwd("123");
    	assertThat(testPwd).isEqualTo(sb.toString());
    }
}