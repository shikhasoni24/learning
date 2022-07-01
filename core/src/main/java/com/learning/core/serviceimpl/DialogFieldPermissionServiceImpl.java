package com.learning.core.serviceimpl;
import javax.jcr.RepositoryException;

import javax.jcr.Session;
import javax.jcr.security.AccessControlManager;
import javax.jcr.security.Privilege;
import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.JackrabbitAccessControlList;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.commons.jackrabbit.authorization.AccessControlUtils;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import com.learning.core.service.DialogFieldPermissionService;

@Component(service=DialogFieldPermissionService.class,immediate=true)
public class DialogFieldPermissionServiceImpl implements DialogFieldPermissionService {

	@Override
	public void modifyNodePermission(Resource resPath,String groupName,Session session) {
		String path=resPath.getPath();
		AccessControlManager acm;
		try {
			acm = session.getAccessControlManager();
			UserManager um =((JackrabbitSession)session).getUserManager();
			Authorizable authorizable=um.getAuthorizable(groupName);
			JackrabbitAccessControlList acl=AccessControlUtils.getAccessControlList(session, path);
			Privilege[] privilege=AccessControlUtils.privilegesFromNames(session, Privilege.JCR_READ);
			acl.addEntry(authorizable.getPrincipal(), privilege, false);
			acm.setPolicy(acl.getPath(), acl);
			session.save();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
}
