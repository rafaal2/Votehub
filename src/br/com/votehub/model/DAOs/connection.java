package br.com.votehub.model.DAOs;

import java.sql.Connection;


public class connection {
	public static void main(String[] args) {
		Connection conn = DB.getConnection();
		DB.closeConnection();
				
	}

}
