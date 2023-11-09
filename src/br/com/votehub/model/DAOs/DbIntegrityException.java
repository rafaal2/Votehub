package br.com.votehub.model.DAOs;

public class DbIntegrityException extends RuntimeException {
	static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}

}
