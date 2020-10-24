package pre.task.traffic.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pre.task.traffic.member.dto.MemberDto;
import org.apache.ibatis.session.SqlSession;

@Repository
public class MemberDao {
	@Autowired
	private SqlSession sqlSession;
	
	String PRE_FIX = "pre.task.traffic.member.dao.mapper.Member.";
	
	public int insertMember(MemberDto memberDto) {
		return sqlSession.insert(PRE_FIX + "insertMember", memberDto);
	}
	
	public String selectMemberInfo(MemberDto memberDto) {
		return sqlSession.selectOne(PRE_FIX + "selectMemberInfo", memberDto);
	}
}