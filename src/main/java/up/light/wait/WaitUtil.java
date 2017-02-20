package up.light.wait;

/**
 * @version 1.0
 */
public abstract class WaitUtil {
	public static final int WAIT_SHORT = 1;
	public static final int WAIT_MEDIUM = 5;
	public static final int WAIT_LONG = 10;


	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
