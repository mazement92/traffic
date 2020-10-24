package pre.task.traffic.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pre.task.traffic.common.LoginRequired;
import pre.task.traffic.info.dto.StationInfoDto;
import pre.task.traffic.info.service.InfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(value = "/info")
public class InfoController { 
	@Autowired
	private InfoService infoService;
	
	@LoginRequired
	@RequestMapping(value = "/infoMain", method=RequestMethod.GET) 
	public String infoMain() { 
		return "info/infoMain"; 
	}
	
	@LoginRequired
	@RequestMapping(value = "/insertTrafficData", method=RequestMethod.POST) 
	public @ResponseBody Map<String, Object> insertTrafficData(@RequestBody Map<String, String> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int resultCnt = infoService.insertTrafficData(param.get("dataPath"));
			resultMap.put("msg", resultCnt + "개 역에 대한 정보를 입력하였습니다. ");
		} catch(Exception e) {
			resultMap.put("msg", "데이터 입력에 실패하였습니다. " + e.getMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	@LoginRequired
	@RequestMapping(value = "/ajaxDailyAvg", method=RequestMethod.GET) 
	public String ajaxDailyAvg(Model model) {
		List<StationInfoDto> resultList = infoService.selectDailyAvgStationUser();
		model.addAttribute("resultList", resultList);
		return "info/ajaxDailyAvg";
	}
	
	@LoginRequired
	@RequestMapping(value = "/ajaxMonthlyAvg", method=RequestMethod.GET) 
	public String ajaxMonthlyAvg(Model model) {
		StationInfoDto result = infoService.selectMonthlyAvgStationUser();
		model.addAttribute("result", result);
		return "info/ajaxMonthlyAvg";
	}
	
	@LoginRequired
	@RequestMapping(value = "/ajaxMaxDiff", method=RequestMethod.GET) 
	public String ajaxMaxDiff(Model model) {
		StationInfoDto result = infoService.selectMaxDiffStationUser();
		model.addAttribute("result", result);
		return "info/ajaxMaxDiff";
	}
}
