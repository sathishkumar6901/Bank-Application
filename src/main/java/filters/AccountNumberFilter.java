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

@WebFilter(urlPatterns = {"/Deposit","/FundTransfer","/GetTransactionOTP","/ResetTransactionPassword","/ResetCardPin","/GetPinOTP"})
public class AccountNumberFilter extends HttpFilter implements Filter {

    public AccountNumberFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	Pattern p;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("accountNumber-filter");
		String accountNumber = req.getParameter("accountNumber");
		Matcher m = p.matcher(accountNumber);
		if (m.matches()) {
			System.out.println("accountNumber valid");
			chain.doFilter(request, response);
		} else {
			System.out.println("accountNumber invalid-filter");
			response.getWriter().println("-4");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		p = Pattern.compile("[1-9]{1}[0-9]{4,8}");
	}

}
