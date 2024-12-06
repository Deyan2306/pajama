package dark.cat.utils;

public class PajamaLogger {

    public static void log(String message) {
        System.out.println("[+] " + message);
    }

    public static void error(String message) {
        System.out.println("[-] " + message);
    }

}
