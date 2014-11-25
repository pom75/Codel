package codel.as.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import codel.as.util.AuthUtils;
import codel.as.util.PathUtils;

/*
 * doc from http://otndnld.oracle.co.jp/document/products/as10g/101300/B25221_03/web.1013/b14426/filters.htm
 */
public class AuthentificationFilter implements Filter {

	private static Logger log = Logger.getLogger("AuthentificationFilter");
	
	FilterConfig config;

	  public void setFilterConfig(FilterConfig config) {
	    this.config = config;
	  }

	  public FilterConfig getFilterConfig() {
	    return config;
	  }

	// FIXME: use slfh
	/*
	 * private static Logger log = LoggerFactory.getLogger(myfile.class);
	 */

	@Override
	public void destroy() {
		log.info("Destroy Authentification filter");
	}

	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info(">>>> checking authentification");
		
		// http://stackoverflow.com/questions/10551694/how-to-access-a-running-servlet-filter/10551806#10551806
		ServletContext ctx = getFilterConfig().getServletContext();
		HttpSession session = ((HttpServletRequest) request).getSession();
		// FIXME CHECK!!!
		if(ctx.getAttribute(AuthUtils.SECRET).equals(session.getAttribute(AuthUtils.SECRET)))
			
		chain.doFilter(request, response);
		else {
			RequestDispatcher rd = request.getRequestDispatcher(PathUtils.LOGIN_SERVLET);
			
			
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("Create Authentification filter");
	}

}
