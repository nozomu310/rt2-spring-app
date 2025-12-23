package jp.co.sss.crud.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;

@Component
public class AccountCheckFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String requestURL = request.getRequestURI();

		if (requestURL.indexOf("/html/") != -1 || requestURL.indexOf("/css/") != -1 || requestURL.indexOf("/img/") != -1
				|| requestURL.indexOf("/js/") != -1) {
			chain.doFilter(request, response);
			return;

		}
		
		if(requestURL.endsWith("/noauth")||requestURL.endsWith("/logout")) {
			chain.doFilter(request, response);
			return;
		}

		if (requestURL.endsWith("/regist/input") || requestURL.endsWith("/delete/check")||requestURL.endsWith("/deletedFlg/check")) {

			HttpSession session = request.getSession();
			EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");
			Integer authority = loginUser.getAuthority();

			if (authority == 2) {
				chain.doFilter(request, response);
			} else {
				
				response.sendRedirect("/spring_crud/noauth");
			}

		} else if (requestURL.endsWith("/update/input")) {
			HttpSession session = request.getSession();
			EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");
			Integer empId = loginUser.getEmpId();
			Integer authority = loginUser.getAuthority();
			
			String empIdStr = request.getParameter("empId");
			Integer targetEmpId = Integer.valueOf(empIdStr);

			if (authority == 2 || empId == targetEmpId) {
				chain.doFilter(request, response);
			} else {
				response.sendRedirect("/spring_crud/noauth");
			}

		} else {
			chain.doFilter(request, response);
		}
	}

}
