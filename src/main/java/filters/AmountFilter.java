package filters;

import java.io.IOException;
import java.util.regex.Matcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(urlPatterns = {"/Withdraw","/Deposit","/FundTransfer"})
public class AmountFilter extends HttpFilter implements Filter {
  
    public AmountFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("amount-filter");
		String sAmount = req.getParameter("amount");
		
		if(sAmount.length()>7) {
			System.out.println("amount invalid-filter");
			response.getWriter().println("-3");
			return;
		}
		
		int amount = Integer.parseInt(sAmount);		
		if (amount>0 && amount<=1000000) {
			System.out.println("amount valid");
			chain.doFilter(request, response);
		} else {
			System.out.println("amount invalid-filter");
			response.getWriter().println("-3");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
