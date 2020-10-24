package pre.task.traffic.info.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pre.task.traffic.info.dto.StationInfoDto;

@Repository
public class InfoDao {
	@Autowired
	private SqlSession sqlSession;
	
	String PRE_FIX = "pre.task.traffic.info.dao.mapper.Info.";
	
	public int insertTrafficStationData(StationInfoDto stationInfoDto) {
		return sqlSession.update(PRE_FIX + "insertTrafficStationData", stationInfoDto);
	}
	
	public int insertTrafficMonthlyUserData(StationInfoDto stationInfoDto) {
		return sqlSession.update(PRE_FIX + "insertTrafficMonthlyUserData", stationInfoDto);
	}
	
	public List<StationInfoDto> selectDailyAvgStationUser() {
		return sqlSession.selectList(PRE_FIX + "selectDailyAvgStationUser");
	}
	
	public StationInfoDto selectMonthlyAvgStationUser() {
		return sqlSession.selectOne(PRE_FIX + "selectMonthlyAvgStationUser");
	}
	
	public StationInfoDto selectMaxDiffStationUser() {
		return sqlSession.selectOne(PRE_FIX + "selectMaxDiffStationUser");
	}
	
	public String test() {
		return "test";
	}
}