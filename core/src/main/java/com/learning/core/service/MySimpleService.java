package com.learning.core.service;

import java.io.FileNotFoundException;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

public interface MySimpleService {
    
    
    // you can use this service directly with data-sly-use
    // like this example
    // <sly data-sly-use.service="com.adobe.examples.htl.core.service.MySimpleService"/>
    //
    // ${service.simpleValue}
    //
    // https://issues.apache.org/jira/browse/SLING-4554
         
    String getSimpleValue();
     
    boolean isAuthor();

	Map<String, String> readExcel(ResourceResolver resolver);
}