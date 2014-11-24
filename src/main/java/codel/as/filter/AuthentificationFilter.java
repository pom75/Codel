package codel.as.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * doc from http://otndnld.oracle.co.jp/document/products/as10g/101300/B25221_03/web.1013/b14426/filters.htm
 */
public class AuthentificationFilter implements Filter {

	private static Logger log = Logger.getLogger("LoginServlet");

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
		// HERE		
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("Create Authentification filter");
	}

}
