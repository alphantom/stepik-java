package Logging;

import java.util.logging.*;

public class LogConfig {

    /**
     * 1. Логгер с именем "org.stepic.java.logging.ClassA" приниет сообщения всех уровней.
     * 2. Логгер с именем "org.stepic.java.logging.ClassB" принимет только сообщения уровня WARNING и серьезнее.
     * 3. Все сообщения, пришедшие от нижестоящих логгеров на уровень "org.stepic.java",
     * независимо от серьезности сообщения печатаются в консоль в формате XML (*)
     * и не передаются вышестоящим обработчикам на уровнях "org.stepic", "org" и "".
     */
    private static void configureLogging() {
        Logger loggerA = Logger.getLogger("org.stepic.java.logging.ClassA");
        Logger loggerB = Logger.getLogger("org.stepic.java.logging.ClassB");
        Logger loggerMain = Logger.getLogger("org.stepic.java");

        loggerA.setLevel(Level.ALL);
        loggerB.setLevel(Level.WARNING);

        loggerMain.setLevel(Level.ALL);
        loggerMain.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler() ;
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        loggerMain.addHandler(handler);

    }
}
