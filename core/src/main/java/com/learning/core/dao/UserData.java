package com.learning.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learning.core.connectionUtil.JDBCconnection;
import com.learning.core.servlets.DynamicDropdown;

@Component(service=UserData.class, immediate=true)
public class UserData {
	
	@Reference
	JDBCconnection jdbcConnection;
	
	private static Logger LOG = LoggerFactory.getLogger(UserData.class);
	
	public String setHotelData(String hotelName, String hotelState){
		String query="insert into hoteldatas values(?, ?)";
		try {
			LOG.debug("inside try in userdata.class");
			Connection con = jdbcConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, hotelName);
			preparedStatement.setString(2, hotelState);
			int i=preparedStatement.executeUpdate();
			if(i==1){
				return "hotel is registered";
			}
		} catch (SQLException e) {
			LOG.error("exception ",e);
		}
		return null; 
		
		
	}

}
