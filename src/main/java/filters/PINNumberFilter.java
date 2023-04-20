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

@WebFilter(urlPatterns = {"/Withdraw","/ResetCardPin"})
public class PINNumberFilter extends HttpFilter implements Filter {
       
  
    public PINNumberFilter() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Pattern p;
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("pinNumber-filter");
		String pinNumber = req.getParameter("pinNumber");
		System.out.println(pinNumber);
		Matcher m = p.matcher(pinNumber);
		if (m.matches()) {
			System.out.println("pinNumber valid");
			chain.doFilter(request, response);
		} else {
			System.out.println("pinNumber invalid-filter");
			response.getWriter().println("-6");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		p = Pattern.compile("[0-9]{4}");
	}

}
