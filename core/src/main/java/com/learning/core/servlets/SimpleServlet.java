/*

 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.learning.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service=Servlet.class,immediate=true,
           property={
                   Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet for page",
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.resourceTypes=" +"cq:Page", 
 //we are hitting servlet on page only and it will work because (jcr:primarytype is cq:page for page node) so 
 //we will get resource as path of page only which excludes jcr:content	thats'y we have added jcr:content in the below code.
 //if we will write sling:resourcetype then to get the servlet data we have to append jcr:content in 
 //URL only because sling:resourcetype resides in jcr:content, and we need not write jcr:content in the logic code.
 //as per current understanding selector is not mandatory.
                //   "sling.servlet.extensions=" +  "html"
           })
public class SimpleServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();// current page as resource
        String path=resource.getPath();
        String newPath=path+"/jcr:content";
        resp.setContentType("text/plain");
        Resource resource1=req.getResourceResolver().getResource(newPath); // page+jcr:content as resource
        resp.getWriter().write("Title = " + resource1.getValueMap().get(JcrConstants.JCR_TITLE));
    
    }
}
