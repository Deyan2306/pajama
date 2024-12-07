package dark.cat.utils;

/**
 * The {@code PajamaLogger} class provides a lightweight logging utility for
 * the Pajama framework. It offers methods for logging informational and error
 * messages in a simple, standardized format.
 *
 * <p>Logs are output directly to the console, making it suitable for debugging
 * during development. The output format distinguishes between informational
 * logs and error logs for better readability.
 *
 * <p>Usage:
 * <pre>
 *     {@code
 * PajamaLogger.log("Application started successfully.");
 * PajamaLogger.error("Failed to initialize the game loop.");
 *     }
 * </pre>
 *
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
public class PajamaLogger {

    /**
     * Logs an informational message to the console.
     *
     * <p>The message is prefixed with {@code "[+]"} to indicate success or general
     * informational content.
     *
     * @param message the message to log
     */
    public static void log(String message) {
        System.out.println("[+] " + message);
    }

    /**
     * Logs an error message to the console.
     *
     * <p>The message is prefixed with {@code "[-]"} to indicate a failure or issue
     * that requires attention.
     *
     * @param message the error message to log
     */
    public static void error(String message) {
        System.out.println("[-] " + message);
    }

}
