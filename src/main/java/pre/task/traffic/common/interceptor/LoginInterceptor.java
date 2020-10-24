package pre.task.traffic.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pre.task.traffic.common.LoginRequired;
import pre.task.traffic.member.service.TokenService;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private TokenService tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		if( handler instanceof HandlerMethod == false ) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
		if(loginRequired == null) {
			return true;
		} else {
			if(request.getSession().getAttribute("token") != null) {
				String token = request.getSession().getAttribute("token").toString();
		        if(tokenService.validateToken(token)) {  // 토큰의 유효성 검증
			        return true;
		        };
			}
		}
        response.sendRedirect("/");
		return false;
	}
}