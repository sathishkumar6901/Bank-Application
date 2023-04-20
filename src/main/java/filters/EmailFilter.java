package filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/Login","/Register","/AddAdmin","/GetTransactionOTP","/GetPinOTP","/GetLoginOTP"})
public class EmailFilter extends HttpFilter implements Filter {
	
	Pattern p;
	
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String email = req.getParameter("emailId");
		System.out.println("Email-filter");
		Matcher m = p.matcher(email);
		if(m.find()) {
			System.out.println("email valid");
			chain.doFilter(request, response);			
		}
		else {
			System.out.println("email invalid-filter");
			response.getWriter().println("-2");
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		p = Pattern.compile("[a-zA-Z0-9]+[@][a-zA-Z0-9]+[\\.][com]");
	}

}
