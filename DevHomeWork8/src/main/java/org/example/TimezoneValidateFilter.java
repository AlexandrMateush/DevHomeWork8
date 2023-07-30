package org.example;


import java.io.IOException;
import java.util.Arrays;
import java.util.TimeZone;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String timezone = request.getParameter("timezone");
        boolean isValid = isValidTimezone(timezone);
        if (!isValid) {
            response.getWriter().write("Invalid timezone");
            response.setCharacterEncoding("UTF-8");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid timezone");


        } else {
            chain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
    }

    private boolean isValidTimezone(String timezone) {
        String[] availableTimezones = TimeZone.getAvailableIDs();
        boolean timeZoneIndex = Arrays.asList(availableTimezones).contains(timezone);
        return timeZoneIndex ;

    }
}