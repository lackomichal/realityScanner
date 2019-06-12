package sk.lacko.reality_scanner;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class SearchPage extends APage {

    public SearchPage(HtmlPage htmlPage) {
        super(htmlPage);
    }

    @Override
    public List<IPage> process() {
        HtmlForm submitForm = htmlPage.getForms().iterator().next();
        ScriptResult result = htmlPage.executeJavaScript("input[name*='stateName']\" ).val(\"Slovenská republika\");");
//        result.
        return null;
    }
}
