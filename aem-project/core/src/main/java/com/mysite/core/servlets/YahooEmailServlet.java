package com.mysite.core.servlets;

import com.mysite.core.Inter1.EmailService1;
import com.mysite.core.Services.YahooEmailServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class}, property = {
    "sling.servlet.methods=" + HttpConstants.METHOD_GET,
    "sling.servlet.resourceTypes=sling/servlet/default",
    "sling.servlet.selectors=yahoo",
    "sling.servlet.extensions=json",
    "service.vendor" + "=Arpit",
    "service.description" + "=Email Servlet"
})
public class YahooEmailServlet extends SlingAllMethodsServlet {

    @Reference
    EmailService1 emailService1;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String emailResponse = emailService1.sendEmail();
        if (StringUtils.isNotBlank(emailResponse)) {
            response.getWriter().println(emailResponse);
        } else {
            response.getWriter().println("No response found");
        }
    }
}
