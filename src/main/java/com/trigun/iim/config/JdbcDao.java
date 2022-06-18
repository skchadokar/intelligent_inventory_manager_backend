package com.trigun.iim.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class JdbcDao {
	
	DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	// Added to select Backup data source when main data source fails. Also updated
	// NPT-servlet with bean entry
	// Updated calls in other subsequent methods using this data source
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			// printDataSourceStats(dataSource);
		} catch (SQLException s) {
			s.printStackTrace();
		} 
		return conn;
	}

}
