package com.learning.core.servlets;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.felix.scr.annotations.References;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.resolver.Resolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.learning.core.service.MySimpleService;

@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/dropdown/values", })
public class DynamicDropdown extends SlingAllMethodsServlet {

	private static Logger LOG = LoggerFactory.getLogger(DynamicDropdown.class);

	@Reference
	MySimpleService ms;

	private static final long serialVersionUID = 1L;

	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse reponse) {

		ResourceResolver resourceResolver = request.getResourceResolver();
		//String path = "/apps/learning/components/content/helloworld/cq:dialog/content/items/column/items/dropdown";
		//List<String> list = new ArrayList<>();
		List<Resource> resList = new ArrayList<>();

		Map<String, String> m = ms.readExcel(resourceResolver);
		//list.add("shikha");
		//list.add("soni");
		//list.add("Aniket");
		//list.add("Pandey");
		for (String str : m.keySet()) {

			ValueMap vm = new ValueMapDecorator(new HashMap<String, Object>());
			vm.put("text", str);
			vm.put("value", m.get(str));
			resList.add(new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", vm));
		}
		DataSource dataSource = new SimpleDataSource(resList.iterator());
		LOG.info("DataSource name" + DataSource.class.getName());
		request.setAttribute(DataSource.class.getName(), dataSource);

	}
}
