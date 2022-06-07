package desu.nya.desktop;

import desu.nya.desktop.frames.MainFrame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final MainFrame mainFrame;

    public Application(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void run(String... args) {
        mainFrame.setVisible(true);
    }


    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).headless(false).run(args);
    }
}
