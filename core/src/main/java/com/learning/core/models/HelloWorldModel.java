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
package com.learning.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.jcr.resource.api.JcrResourceConstants;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.settings.SlingSettingsService;

import com.day.cq.commons.inherit.InheritanceValueMap;

@Model(adaptables={Resource.class,SlingHttpServletRequest.class})
public class HelloWorldModel  {
	@Inject
	private InheritanceValueMap pageProperties;
    @Inject
    private SlingSettingsService settings;
    
   /* ResourcePath: 
        ➤  If a resource is having a property whose value is a path, you can directly use that property as a resource.
        ➤  You can directly inject a path as a resource using this annotation.

The attributes of the ResourcePath annotation is:
name
injectionStrategy
path
paths[]*/

@Model(adaptables = Resource.class )
public class TestModel {
 
 //directly inject a path as a resource 
   @ResourcePath(path = "/etc/social")
   Resource pathResource;

  @ResourcePath(name = "path")
   Resource resourcePath;

   @ResourcePath(paths = {"/etc/social","/etc/tags"})
   Resource[]  paths;
}

    @Inject @Named(JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY) @Default(values="No resourceType")
    protected String resourceType;

    private String message;

    @PostConstruct
    protected void init() {
    	

        message = "\tHello World!\n";
        message += "\tThis is instance: " + settings.getSlingId() + "\n";
        message += "\tResource type is: " + resourceType + "\n";
        message += "pageProperties is: " + pageProperties + "\n";
        

        
    }

    public String getMessage() {
        return message;
    }
}
