package pre.task.traffic.member.service;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import pre.task.traffic.member.dao.MemberDao;
import pre.task.traffic.member.dto.MemberDto;

@Service
public class MemberService {
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 회원 가입 (회원 정보 저장)
	 * @param memberDto MemberDto
	 * @throws Exception
	 */
	public void insertMember(MemberDto memberDto) throws Exception {
		try {
			String encryptPwd = this.getEncryptMemberPwd(memberDto.getMemberPwd());
            memberDto.setMemberPwd(encryptPwd);
            memberDao.insertMember(memberDto);
        } catch(DuplicateKeyException de) {
        	throw new DuplicateKeyException("이미 등록된 ID입니다. 다른 ID로 회원가입 해주세요.");
        } catch(Exception e) {
        	e.printStackTrace();
        	throw new Exception(e.getMessage());
        }
	}
	
	/**
	 * 로그인 정보 조회
	 * @param memberDto MemberDto
	 * @return String
	 * @throws Exception
	 */
	public String getMemberInfo(MemberDto memberDto) throws Exception {
		try {
			String memberPwd = memberDao.selectMemberInfo(memberDto);
			/* 로그인 정보 확인 */
			String encryptPwd = this.getEncryptMemberPwd(memberDto.getMemberPwd());
			if(!memberPwd.equals(encryptPwd)) {
				throw new Exception("암호가 일치하지 않습니다.");
			}
			String token = tokenService.createToken(memberDto, memberPwd);
			return token;
		} catch(NullPointerException ne) {
			throw new NullPointerException("등록된 회원 정보가 없습니다.");
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * PW 암호화
	 * @param memberPwd String
	 * @return String
	 * @throws Exception
	 */
	public String getEncryptMemberPwd(String memberPwd) throws Exception {
        StringBuffer sb = new StringBuffer();
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
	        byte[] passBytes = memberPwd.getBytes();
	        messageDigest.reset();
	        byte[] digested = messageDigest.digest(passBytes);
	        for(int i = 0; i < digested.length; i++) {
	        	sb.append(Integer.toString((digested[i]&0xff) + 0x100, 16)
	        			.substring(1));
	        }
        } catch(Exception e) {
			e.printStackTrace();
			throw new Exception("PW 암호화에 실패하였습니다.");
		}
		return sb.toString();
	}
}