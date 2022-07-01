package com.learning.core.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.learning.core.service.MathService;
import com.learning.core.service.MyServiceConfiguration;
import com.learning.core.service.MySimpleService;

@Component(immediate = true, name="qqqqqqqqqqq")
@Designate(ocd = MyServiceConfiguration.class)
public class MySimpleServiceImpl implements MySimpleService {

	private MyServiceConfiguration config;
	
	@Reference
	ResourceResolverFactory factory;

	@Reference
	MathService mathService;

	private static final Logger LOG = LoggerFactory.getLogger(MySimpleServiceImpl.class);

	private boolean author;
	@Reference
	private SlingSettingsService settings;

	@Activate
	public void activate(MyServiceConfiguration config) {

		this.config = config;
		author = settings.getRunModes().contains("author");
	}

	public String getSimpleValue() {
		return "hello " + config.configValue();
	}

	public boolean isAuthor() {
		mathService.calculateValue();
		LOG.info("ggggggggggggggg");
		return author;

	}

	@Override
	public Map<String,String> readExcel(ResourceResolver resolver)  
	{
		
		Map<String, String> map = new HashMap<>();
		try {
			//Map<String, Object> params = new HashMap<>();
			//params.put(ResourceResolverFactory.SUBSERVICE, "myUser");
			//ResourceResolver resourceResolver = factory.getServiceResourceResolver(params);
			//ResourceResolver resourceResolver;
			Resource res = resolver.getResource("/content/dam/dropdown/dropdownexcel.xlsx");
			Asset asset = res.adaptTo(Asset.class);
			Rendition rendition=asset.getOriginal();
			//InputStream data=rendition.getStream();
		
		//FileInputStream data = asset.getOriginal().adaptTo(FileInputStream.class);
	    //String inputString = CharStreams.toString(supplier);
		//ZipSecureFile.setMinInflateRatio(-1.0d);
		//LOG.info("data is"  +data);
			InputStream data=rendition.adaptTo(InputStream.class);
			Workbook workbook = new XSSFWorkbook(data);
			Sheet firstSheet = workbook.getSheetAt(0);
			LOG.info("firstSheet is"  +firstSheet.getSheetName());

			// Row row = firstSheet.getRow(1);
			Iterator<Row> rowIterator = firstSheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				if (i != 0) {
					Row row = rowIterator.next();
					map.put(row.getCell(0).toString(), row.getCell(1).toString());
					LOG.info("key is " +row.getCell(0).toString());
					LOG.info("value is " +row.getCell(1).toString());
				}
				i++;
			}

		} catch (IOException e) {
			LOG.error("exception " +e);

			e.printStackTrace();
		}
		return map;
	}



}
