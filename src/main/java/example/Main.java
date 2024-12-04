package example;

import dark.cat.PajamaApplication;
import example.components.CustomGameLoop;

public class Main {

    public static void main(String[] args) throws Exception {
        PajamaApplication.run(CustomGameLoop.class, "example.components");
    }

}
