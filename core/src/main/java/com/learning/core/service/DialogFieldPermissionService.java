package com.learning.core.service;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;

public interface DialogFieldPermissionService {


	public void modifyNodePermission(Resource resPath, String groupName,Session session);
}
