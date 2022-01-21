package in.co.rays.project4.exception;


/**
 *  DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * @author Lalit Kewat
 *
 */
public class DatabaseException extends Exception {
	  /**
     * @param msg
     *            : Error message
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}
