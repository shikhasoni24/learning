package com.learning.core.serviceimpl;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;

public class ConnectToAEM {
	
	public static void main(String[] args) throws RepositoryException {
		Repository repository = JcrUtils.getRepository("http://localhost:4502/crx/server");
		System.out.println("hi");
		Session session = repository.login( new SimpleCredentials("admin", "admin".toCharArray()));
		System.out.println("hi");
		Node root = session.getRootNode();
		System.out.println(root.getPath());

		Node day = root.addNode("sachinn");
		day.setProperty("message", "Adobe CQ is part of the Adobe Digital Marketing Suite!");
	
		//Node node = root.getNode("dayy");
		System.out.println(day.getPath());
		System.out.println(day.getProperty("message").getString());
		session.save(); 
		session.logout();
	}

}
