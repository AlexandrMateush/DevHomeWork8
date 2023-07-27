package org.example;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String timezoneParam = request.getParameter("timezone");

        if (timezoneParam == null || timezoneParam.isEmpty() || !isValidTimezone(timezoneParam)) {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid timezone");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Invalid timezone</h1>");
            response.getWriter().println("</body></html>");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isValidTimezone(String timezone) {
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        return tz.getID().equals(timezone);
    }

}

