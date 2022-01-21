package in.co.rays.project4.exception;


/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * @author Lalit Kewat
 *
 */
public class DuplicateRecordException extends Exception {
	/**
     * @param msg
     *            : Error message
     */
    public DuplicateRecordException(String msg) {
        super(msg);
}
}