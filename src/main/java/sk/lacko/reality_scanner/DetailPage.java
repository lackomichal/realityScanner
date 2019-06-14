package sk.lacko.reality_scanner;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;
import java.util.Map;

public class DetailPage extends APage{

    public DetailPage(HtmlPage htmlPage, Map<String,String> parameters) {
        super(htmlPage);
    }

    @Override
    public List<IPage> process() {
        return null;
    }
}


