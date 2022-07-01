package com.learning.core.models;

import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import com.adobe.cq.wcm.core.components.models.Image;

@Model(
adaptables = SlingHttpServletRequest.class,
adapters = { InheritedImageCoreInterface.class},
resourceType = InheritedImageCoreImpl.RESOURCE_TYPE,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class InheritedImageCoreImpl implements InheritedImageCoreInterface {
	
  static final String RESOURCE_TYPE = "learning/components/content/testcore";
  
  @Self
  private SlingHttpServletRequest request;
  

  // With sling inheritance (sling:resourceSuperType) we can adapt the current resource to the Image class
  // this allows us to re-use all of the functionality of the Image class, without having to implement it ourself
  // see https://github.com/adobe/aem-core-wcm-components/wiki/Delegation-Pattern-for-Sling-Models
  
  @Self
  @Via(type = ResourceSuperType.class)
  private Image image;

	@ValueMapValue
	private String text;
	
	
	@PostConstruct
	protected void init(){
		Node node=request.getResourceResolver().getResource("/content").adaptTo(Node.class);
		
			try {
				Node node1=node.addNode("sampleData","nt:unstructured");
				for(int i=1;i<=5000;i++){
					
					Node childNode=node1.addNode("node"+i,"nt:unstructured");
					childNode.setProperty("id", "1111"+i);
					childNode.setProperty("name", "node"+i);
				}
				node.getSession().save();
			} 
			
			catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	@Override
	public boolean displayPopupTitle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAlt() {
		// TODO Auto-generated method stub
		return null!=image?image.getAlt():null;
	}

	@Override
	public String getFileReference() {
		// TODO Auto-generated method stub
		return null!=image?image.getFileReference():null;
	}

	@Override
	public String getJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLink() {
		// TODO Auto-generated method stub
		return null!=image?image.getLink():null;
	}

	@Override
	public String getSrc() {
		// TODO Auto-generated method stub
		return null!=image?image.getSrc():null;
	}

	

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null!=image?image.getTitle():null;
	}

}
