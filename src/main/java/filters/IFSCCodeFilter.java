package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/Deposit","/FundTransfer"})
public class IFSCCodeFilter extends HttpFilter implements Filter {
       
   
    public IFSCCodeFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("ifscCode-filter");
		String ifscCode = req.getParameter("ifscCode");
		if (ifscCode.equals("ZSGS000CH0")) {
			System.out.println("ifscCode valid");
			chain.doFilter(request, response);
		} else {
			System.out.println("ifscCode invalid-filter");
			response.getWriter().println("-4");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
