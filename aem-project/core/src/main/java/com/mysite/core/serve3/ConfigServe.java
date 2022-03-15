package com.mysite.core.serve3;

import com.mysite.core.serve1.Service;
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
        "sling.servlet.selectors=ayush",
        "sling.servlet.extensions=html",
        "service.vendor" + "=Name",
        "service.description" + "=Email Servlet"
})
public class ConfigServe extends SlingAllMethodsServlet {

    @Reference
    Service service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String emailResponse = service.author();
        if (StringUtils.isNotBlank(emailResponse)) {
            response.getWriter().println(emailResponse);
        } else {
            response.getWriter().println("No response found");
        }
    }
}