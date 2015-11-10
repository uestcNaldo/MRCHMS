package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 11656 on 2015/11/9.
 */
public class OperatingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
