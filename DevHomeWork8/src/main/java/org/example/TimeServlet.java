package org.example;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String timezoneParam = request.getParameter("timezone");
        TimeZone timezone = parseTimeZone(timezoneParam);

        Date currentDate = new Date();
        String currentTime = convertToTimeZoneFormat(currentDate, timezone);

        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Поточний час (" + timezone.getID() + ")</h1>");
        response.getWriter().println("<p>" + currentTime + "</p>");
        response.getWriter().println("</body></html>");
    }

    private TimeZone parseTimeZone(String timezoneParam) {
        if (timezoneParam != null && timezoneParam.matches("^UTC[+-]\\d{1,2}$")) {
            try {
                int totalOffset = Integer.parseInt(timezoneParam.substring(3));
                if (totalOffset >= -12 * 60 && totalOffset <= 14 * 60) {
                    if( totalOffset < 0 && totalOffset > -13){
                        return TimeZone.getTimeZone("GMT" + totalOffset);
                    }return TimeZone.getTimeZone("GMT" + (totalOffset > 0 && totalOffset < 15 ? "+" : " ") + totalOffset);
                }
            } catch (NumberFormatException e) {
            }
        }
        return TimeZone.getTimeZone("UTC");
    }

    private String convertToTimeZoneFormat(Date date, TimeZone timezone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(timezone);
        String formattedDate = sdf.format(date);
        int offsetInMillis = timezone.getOffset(date.getTime());
        int offsetHours = offsetInMillis / (60 * 60 * 1000);
        return formattedDate.replace("GMT", "UTC") ;
    }
}