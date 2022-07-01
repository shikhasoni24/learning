package com.learning.core.schedulers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;

@Component(service=Runnable.class,immediate=true,property={"scheduler.expression=*/10 * * * * ?"})
public class ReadJSONDataFromDam implements Runnable{
	@Activate
	protected void activate(){
		logger.info("hellllllllllllllllllllllloo");
	}
	
	@Reference
	ResourceResolverFactory factory;
	
	private static Logger logger=LoggerFactory.getLogger(ReadJSONDataFromDam.class);

	@Override
	public void run() {
		logger.info(" run hellllllllllllllllllllllloo");
		try {
		String path="/content/dam/jsondata/data.json";
		String desPath="/content/learning/en/demo/jcr:content/root/responsivegrid_72202793/text";
		logger.info(" 46 ");

		Map<String,Object> map=new HashMap<>();
		map.put(ResourceResolverFactory.SUBSERVICE, "myUser");
		logger.info(" 47 ");

		ResourceResolver resolver=factory.getServiceResourceResolver(map);
		Resource res=resolver.getResource(path);
		Resource res1=resolver.getResource(desPath);
		logger.info(" 50 ");
		Asset asset=res.adaptTo(Asset.class);
		Rendition damOriginalRendition = asset.getOriginal();
		logger.info("45"+damOriginalRendition);
		InputStream data=damOriginalRendition.adaptTo(InputStream.class);//read any file 
		logger.info("47"+ data);
		InputStreamReader input=new InputStreamReader(data);
		BufferedReader reader = new BufferedReader(input);
		StringBuilder stringBuilder=new StringBuilder();
		String content="";
		while((content=reader.readLine())!=null){
			logger.info("content is"+content);
			stringBuilder.append(content);
		}
		logger.info("54"+stringBuilder.toString());
		String str=stringBuilder.toString();
		logger.info("72"+ str);
		Node node=res1.adaptTo(Node.class);
		node.setProperty("text", str);
		node.getSession().save();
		} catch (LoginException | IOException | RepositoryException e) {
		logger.error("exception" +e);
			e.printStackTrace();
		}		
	}

}
