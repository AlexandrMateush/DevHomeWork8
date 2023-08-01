package org.example;


import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TimeServletTest {

    @Test
    public void testTimeServletWithDefaultTimezone() throws Exception {
        TimeServlet timeServlet = new TimeServlet();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        timeServlet.doGet(request, response);

        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains("UTC"));
    }

    @Test
    public void testTimeServletWithValidTimezone() throws Exception {
        // Arrange
        TimeServlet timeServlet = new TimeServlet();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        String timezoneParam = "UTC";
        request.setParameter("timezone", timezoneParam);

        timeServlet.doGet(request, response);


        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains(timezoneParam));
    }

}