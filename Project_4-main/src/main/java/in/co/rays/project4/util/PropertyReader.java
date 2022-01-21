package in.co.rays.project4.util;

import java.util.ResourceBundle;

/**
 * Read the property value from application
 * @author Lalit Kewat
 *
 */
public class PropertyReader {
	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.Project_4.Bundle.system");

	/**
	 * Return value of key
	 *
	 */

	public static String getValue(String key) {

		String val = null;

		try {
			val = rb.getString(key);
		} catch (Exception e) {
			val = key;
		}

		return val;

	}

	/**
	 * Gets String after placing param values
	 *
	 */
	public static String getValue(String key, String param) {
		String msg = getValue(key);
		msg = msg.replace("{0}", param);
		return msg;
	}

	/**
	 * Gets String after placing params values
	 *
	 */
	public static String getValue(String key, String[] params) {
		String msg = getValue(key);
		for (int i = 0; i < params.length; i++) {
			msg = msg.replace("{" + i + "}", params[i]);
		}
		return msg;
	}

	/**
	 * Test method
	 *
	 */

	public static void main(String[] args) {
		String[] params = { "Roll No" };
		System.out.println(PropertyReader.getValue("error.require", params));
	}

}
