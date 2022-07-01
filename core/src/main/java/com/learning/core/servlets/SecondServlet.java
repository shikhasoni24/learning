package com.learning.core.servlets;

import java.util.Iterator;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@SuppressWarnings("deprecation")
@Component(name="my second servlet", service = Servlet.class, property = { "sling.servlet.resourceTypes=", "sling.servlet.methods=GET",
		"sling.servlet.selectors=articles", "sling.servlet.extension=json" })
public class SecondServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static Logger LOG = LoggerFactory.getLogger(SecondServlet.class);

	@SuppressWarnings("deprecation")
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		String rootPage = "/content/";

		ResourceResolver resolver = request.getResourceResolver();
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page articlePage = pageManager.getPage(rootPage);

		JSONArray pageList = new JSONArray();

		if (articlePage != null) {
			Iterator<Page> childPages = articlePage.listChildren();
			while (childPages.hasNext()) {
				Page page = childPages.next();

				JSONObject pageObj = new JSONObject();
				try {
					pageObj.put("title", page.getTitle());
					pageObj.put("description", page.getDescription());
					pageObj.put("path", page.getPath() + ".html");
					pageObj.put("createdDate", page.getLastModified().getTime());
					Resource imageResource = resolver.getResource(page.getPath() + "");
					pageObj.put("thumbnail", imageResource.getValueMap().get("fileReference", String.class));
					pageList.put(pageObj);
				} catch (Exception e) {
			  }
		   }
		}
		response.setContentType("application/json");
	}
}
