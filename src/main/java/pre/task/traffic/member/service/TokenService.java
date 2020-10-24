package pre.task.traffic.member.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pre.task.traffic.member.dto.MemberDto;

@Service
public class TokenService {
	
	private final String SECRET_KEY = "secretKey1234asdfqwer4321";
	
	/**
	 * 암호 검증 후 토큰 생성
	 * @param memberId int
	 * @param memberPwd String
	 * @return String
	 * @throws Exception
	 */
	public String createToken(MemberDto memberDto, String memberPwd) throws Exception {		
		/* 토큰 생성 */
		try {
	        SignatureAlgorithm  signatureAlgorithm= SignatureAlgorithm.HS256;
	        JwtBuilder builder = Jwts.builder()
	        		.setSubject(memberDto.getMemberId())
	        		.signWith(signatureAlgorithm, SECRET_KEY);
	        return builder.compact();
		} catch(Exception e) {
			throw new Exception("토큰 생성에 실패하였습니다.");
		}
    }
	
	/**
	 * 토큰 정보 검증
	 * @param token String
	 * @return boolean
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}