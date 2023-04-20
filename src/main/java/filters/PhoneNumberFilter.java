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

@WebFilter(urlPatterns = { "/Register","/AddAdmin" })
public class PhoneNumberFilter extends HttpFilter implements Filter {

	public PhoneNumberFilter() {
		super();

	}

	Pattern p;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("phoneNumber-filter");
		String phoneNumber = req.getParameter("phoneNumber");
		Matcher m = p.matcher(phoneNumber);
		if (m.matches()) {
			System.out.println("phonenumber valid");
			chain.doFilter(request, response);
		} else {
			System.out.println("phoneNumber invalid-filter");
			response.getWriter().println("-3");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		p = Pattern.compile("[1-9]{1}[0-9]{9}");
	}

}
