package com.example.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component("customAuthenticationEntryPoint")
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        /** Response Header */
        response.setHeader("Content-Type", "application/json");
        /** Response Code*/
        int intStatus = response.getStatus();
        response.setStatus(intStatus==200?HttpServletResponse.SC_UNAUTHORIZED:HttpServletResponse.SC_FORBIDDEN);

        /**Response Body*/
        Map<String, Object> data = new HashMap<>();
        data.put("status", false);
        data.put("timestamp", Calendar.getInstance().getTime());
        if (intStatus==HttpServletResponse.SC_UNAUTHORIZED){
            data.put("error", authException.getMessage()+ " SC_UNAUTHORIZED");
        }
        else if(intStatus==HttpServletResponse.SC_FORBIDDEN){
            data.put("error", authException.getMessage()+ " SC_FORBIDDEN");
        }
        response.getOutputStream().println(mapper.writeValueAsString(data));
    }
}
