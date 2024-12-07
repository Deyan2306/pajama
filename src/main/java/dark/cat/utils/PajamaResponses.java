package dark.cat.utils;

/**
 * The {@code PajamaResponses} enum provides a centralized repository of response codes
 * and messages used throughout the Pajama framework. These responses are designed to
 * standardize error, success, and informational messages, making the application more
 * maintainable and consistent.
 *
 * <p>Each response contains:
 * <ul>
 *   <li>A unique response code ({@code code}) for identification.</li>
 *   <li>A descriptive message ({@code message}) explaining the response.</li>
 * </ul>
 *
 * <p>Usage:
 * <pre>
 *     {@code
 * String errorMessage = PajamaResponses.NO_GAME_LOOP_FOUND.getMessage();
 * String errorCode = PajamaResponses.NO_GAME_LOOP_FOUND.getCode();
 *     }
 * </pre>
 *
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
public enum PajamaResponses {

    /**
     * Indicates that the {@link Runnable} interface is not implemented by a class
     * annotated with {@code @GameLoop}.
     */
    GAME_LOOP_HAS_NOT_IMPLEMENTED_RUNNABLE("ERR001", "@GameLoop class must implement Runnable"),

    /**
     * Indicates that no class annotated with {@code @GameLoop} was found in the application context.
     */
    NO_GAME_LOOP_FOUND("ERR002", "No @GameLoop class found!"),

    /**
     * Message indicating that the Pajama application has started successfully.
     */
    APPLICATION_STARTED_SUCCESSFULLY("SUC001", "Pajama Application started successfully!"),

    /**
     * Message indicating that no component was found for a required dependency.
     */
    NO_COMPONENT_FOUND_FOR("NCF", "No component found for:");

    /**
     * The unique response code associated with this response.
     */
    private final String code;

    /**
     * The human-readable message describing this response.
     */
    private final String message;

    /**
     * Constructs a {@code PajamaResponses} enum instance.
     *
     * @param code the unique response code
     * @param message the descriptive message
     */
    PajamaResponses(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Retrieves the unique response code for this response.
     *
     * @return the response code
     */
    public String getCode() {
        return code;
    }

    /**
     * Retrieves the descriptive message for this response.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
