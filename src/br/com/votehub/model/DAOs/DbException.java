package br.com.votehub.model.DAOs;

import java.nio.file.AccessDeniedException;

import javax.naming.CommunicationException;

public class DbException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public DbException(String msg) {
		super(msg);
	}
	
	public DbException(String msg, AccessDeniedException causa) {
        super(msg, causa);
    }

    public DbException(String msg, CommunicationException causa) {
        super(msg, causa);
    }
	

}
