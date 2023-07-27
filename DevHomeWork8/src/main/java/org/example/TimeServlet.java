package org.example;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String timezoneParam = request.getParameter("timezone");
        java.util.TimeZone timezone = java.util.TimeZone.getTimeZone("UTC");

        if (timezoneParam != null && !timezoneParam.isEmpty()) {
            timezone = java.util.TimeZone.getTimeZone(timezoneParam);
        }

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(timezone);
        String currentTime = sdf.format(currentDate);

        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Поточний час (" + timezone.getID() + ")</h1>");
        response.getWriter().println("<p>" + currentTime + "</p>");
        response.getWriter().println("</body></html>");
    }
}