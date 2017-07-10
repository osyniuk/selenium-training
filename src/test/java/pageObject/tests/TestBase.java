package pageObject.tests;

import pageObject.app.Application;

public class TestBase {

    public static ThreadLocal<Application>tlApp = new ThreadLocal<>();
    public Application app;

    public void start() {
        if (tlApp.get() !=null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {app.stop(); app=null;}
        ));
    }
}
