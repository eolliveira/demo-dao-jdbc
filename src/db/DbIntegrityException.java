package db;

//Exe��o personalizada
public class DbIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
	
}
