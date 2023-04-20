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

@WebFilter(urlPatterns = {"/Withdraw","/GetPinOTP"})
public class CVVNumberFilter extends HttpFilter implements Filter {
       
   
	public void destroy() {
		// TODO Auto-generated method stub
	}
	Pattern p;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("cvvNumber-filter");
		String cvvNumber = req.getParameter("cvvNumber");
		Matcher m = p.matcher(cvvNumber);
		if (m.matches()) {
			System.out.println("cvvNumber valid");
			chain.doFilter(request, response);
		} else {
			System.out.println("cvvNumber invalid-filter");
			response.getWriter().println("-1");
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		p = Pattern.compile("[0-9]{3}");
	}

}
