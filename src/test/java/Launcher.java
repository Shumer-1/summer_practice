import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.TagFilter;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Класс-лаунчер, используемый
 * для запуска блоков тестов
 * или всех тестов.
 */
public class Launcher {
    /**
     * Метод main класса Launcher
     */
    public static void main(String[] args) {
        System.out.println("profile, authorization, search or all");
        var scanner = new Scanner(System.in);
        String chosenTag = scanner.next();

        switch (chosenTag) {
            case ("profile"), ("authorization"), ("search"), ("all"):
                break;
            default:
                System.out.println("profile, authorization, search or all");
                return;
        }

        var launcher = LauncherFactory.create();

        var summaryGeneratingListener = new SummaryGeneratingListener();

        var request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(DiscoverySelectors.selectPackage("tests"));
        if (!chosenTag.equals("all")) {
            request.filters(TagFilter.includeTags(chosenTag));
        }

        launcher.execute(request.build(), summaryGeneratingListener);

        summaryGeneratingListener.getSummary().printTo(new PrintWriter(System.out));
    }
}
