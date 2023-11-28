package br.com.votehub.model.DAOs;

import java.sql.Connection;


public class connection {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Connection conn = DB.getConnection();
		DB.closeConnection();
				
	}

}

