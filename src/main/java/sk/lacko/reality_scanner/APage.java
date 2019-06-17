package sk.lacko.reality_scanner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class APage implements IPage {
    protected HtmlPage htmlPage;
    @Autowired
    protected WebClient webClient;

    public APage(HtmlPage htmlPage) {
        this.htmlPage = htmlPage;
    }
}
