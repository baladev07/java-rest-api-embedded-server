package com.Util;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class PerformanceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            FetchRequestData.fetchRequestData(servletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
