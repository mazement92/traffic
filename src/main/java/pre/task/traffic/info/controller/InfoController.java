package pre.task.traffic.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pre.task.traffic.common.LoginRequired;
import pre.task.traffic.info.dto.StationInfoDto;
import pre.task.traffic.info.service.InfoService;

@Controller
@RequestMapping(value = "/info")
public class InfoController { 
	@Autowired
	private InfoService infoService;
	
	/**
	 * 조회 페이지 메인
	 * @return String
	 */
	@LoginRequired
	@RequestMapping(value = "/infoMain", method=RequestMethod.GET) 
	public String infoMain() { 
		return "info/infoMain"; 
	}
	
	/**
	 * 교통정보 데이터 입력
	 * @param param Map<String, String>
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@LoginRequired
	@RequestMapping(value = "/insertTrafficData", method=RequestMethod.POST) 
	public @ResponseBody Map<String, Object> insertTrafficData(@RequestBody Map<String, String> param) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int resultCnt = infoService.insertTrafficData(param.get("dataPath"));
			resultMap.put("msg", resultCnt + "개 역에 대한 정보를 입력하였습니다. ");
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("데이터 입력에 실패하였습니다. " + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 일 평균 인원 상위 10개 역 명, 인원 수 조회
	 * @return List<Map<String, Object>>
	 */
	@LoginRequired
	@RequestMapping(value = "/ajaxDailyAvg", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> ajaxDailyAvg() {
		List<StationInfoDto> stationInfoDtoList = infoService.selectDailyAvgStationUser();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		for(StationInfoDto station : stationInfoDtoList) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("stationName", station.getStationName());
			resultMap.put("dailyAvgUser", station.getDailyAvgUser());
			resultList.add(resultMap);
		}
		return resultList;
	}
	
	/**
	 * 월 인원 평균이 가장 작은 역 명, 인원 수 조회
	 * @return Map<String, Object>
	 */
	@LoginRequired
	@RequestMapping(value = "/ajaxMonthlyAvg", method=RequestMethod.GET) 
	public @ResponseBody Map<String, Object> ajaxMonthlyAvg() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		StationInfoDto stationInfoDto = infoService.selectMonthlyAvgStationUser();
		
		if(stationInfoDto != null) {
			resultMap.put("stationName", stationInfoDto.getStationName());
			resultMap.put("monthlyAvgUser", stationInfoDto.getMonthlyAvgUser());
		}
		return resultMap;
	}
	
	/**
	 * 월 최대 최소 인원 차이가 가장 큰 역 명 조회
	 * @return Map<String, Object>
	 */
	@LoginRequired
	@RequestMapping(value = "/ajaxMaxDiff", method=RequestMethod.GET) 
	public @ResponseBody Map<String, Object> ajaxMaxDiff() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		StationInfoDto stationInfoDto = infoService.selectMaxDiffStationUser();
		if(stationInfoDto != null) {
			resultMap.put("stationName", stationInfoDto.getStationName());
		}
		return resultMap;
	}
}
