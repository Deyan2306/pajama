package dark.cat.utils;

public enum PajamaResponses {

    GAME_LOOP_HAS_NOT_IMPLEMENTED_RUNNABLE("ERR001", "@GameLoop class must implement Runnable"),
    NO_GAME_LOOP_FOUND("ERR002", "No @GameLoop class found!"),
    APPLICATION_STARTED_SUCCESSFULLY("SUC001", "Pajama Application started successfully!"),
    NO_COMPONENT_FOUND_FOR("NCF", "No component found for:");

    private final String code;
    private final String message;

    PajamaResponses(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
