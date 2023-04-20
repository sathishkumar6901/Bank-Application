package filters;

import java.io.IOException;
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

@WebFilter(
		urlPatterns = {"/Login","/Register","/AddAdmin","/FundTransfer","/ResetTransactionPassword","/ResetLoginPassword"})
public class PasswordFilter extends HttpFilter implements Filter {
     
	public void destroy() {
		// TODO Auto-generated method stub
	}
	Pattern p;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("password-filter");
		String password = req.getParameter("password");
		System.out.println("password is:" +password);
		Matcher m = p.matcher(password);
		if(m.matches()) {
			System.out.println("password valid");
			chain.doFilter(request, response);			
		}
		else {
			System.out.println("password invalid-filter");
			response.getWriter().println("-5");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,15}$";
		p = Pattern.compile(regex);
	}

}
