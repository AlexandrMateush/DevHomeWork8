package org.example;


import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;




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

    @Test
    public void testInvalidTimezone() throws ServletException, IOException {
        String invalidTimezone = "Invalid_Timezone";

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("timezone", invalidTimezone);

        TimezoneValidateFilter filter = new TimezoneValidateFilter();


        filter.doFilter(request, response, (req, res) -> {});

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testEmptyTimezone() throws ServletException, IOException {
        // Пустий часовий пояс
        String emptyTimezone = "";

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("timezone", emptyTimezone);

        TimezoneValidateFilter filter = new TimezoneValidateFilter();

        filter.doFilter(request, response, (req, res) -> {});

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
    }
}