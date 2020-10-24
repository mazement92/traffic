package pre.task.traffic.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	/**
	 * 메인 페이지
	 * @return String
	 */
	@RequestMapping(value = "/", method=RequestMethod.GET) 
	public String main(HttpServletRequest request) { 
		if(request.getSession().getAttribute("token") != null) {
			return "info/infoMain";
		}
		return "main"; 
	}
}