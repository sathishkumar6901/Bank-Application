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


@WebFilter(urlPatterns = {"/Register","/AddAdmin"})
public class NameFilter extends HttpFilter implements Filter {
 
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	Pattern p;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String name = req.getParameter("name");
		System.out.println("name-filter");
		Matcher m = p.matcher(name);
		if(m.matches()) {
			System.out.println("name valid");
			chain.doFilter(request, response);			
		}
		else {
			System.out.println("name invalid-filter");
			response.getWriter().println("-1");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		p = Pattern.compile("[a-zA-Z0-9]{5,15}");
	}

}
