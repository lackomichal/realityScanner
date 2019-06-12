package sk.lacko.reality_scanner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @Autowired
    private WebClient webClient;

    @Autowired
    private PageRunner pageRunner;

    @Override
    public void run(String... args) throws Exception {

        Options options = new Options();

        Option input = new Option("p", "page", true, "Page to scan. Supported pages: www.reality.sk");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("s", "search", true, "What to search. Supported values: F - flat, H - house");
        output.setRequired(false);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String[] inputFilePath = cmd.getOptionValues("page");

        for (int i = 0; i < inputFilePath.length; i++) {
            HtmlPage htmlPage = webClient.getPage(inputFilePath[i]);
            pageRunner.runSearch(new SearchPage(htmlPage));
        }

        String[] outputFilePath = cmd.getOptionValues("search");

        System.exit(1);

    }
}
