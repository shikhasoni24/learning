package com.learning.core.models;

import javax.servlet.http.HttpServletRequest;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import com.day.cq.wcm.api.Page;

@Model(adaptables=HttpServletRequest.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class Testing {


	@ScriptVariable
		Page currentPage;
		
		 public String getPageTitle() {
		        return currentPage.getTitle();
		    }
		}

	
