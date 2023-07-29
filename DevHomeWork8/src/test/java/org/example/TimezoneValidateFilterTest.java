package org.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.jupiter.api.Test;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class TimezoneValidateFilterTest {
    @Test
    public void testValidTimezone() throws ServletException, IOException {
        String validTimezone = "UTC+2";

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("timezone", validTimezone);

        TimezoneValidateFilter filter = new TimezoneValidateFilter();

        filter.doFilter(request, response, (req, res) -> {});

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }
}