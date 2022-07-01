package com.learning.core.connectionUtil;

import java.sql.Connection;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.commons.datasource.poolservice.DataSourcePool;

@Component(service=JDBCconnection.class, immediate=true)
public class JDBCconnection {
	
	private final Logger log=LoggerFactory.getLogger(JDBCconnection.class);
	
@Reference
DataSourcePool dataSourcePool;

  public Connection getConnection(){
	  
	  DataSource dataSource=null;
	  Connection con=null;	
	  try{
		  log.debug("Inside try");
		  dataSource=(DataSource) dataSourcePool.getDataSource("Employee");
		  con=dataSource.getConnection();
		  log.debug("connection established");
		  
	  }
	  catch(Exception e){
		  log.error("Error in JDBC connection method   "+e.getMessage());
		  log.error(e.getCause().toString());
	  }
		return con;
	  }
  	
  }
	