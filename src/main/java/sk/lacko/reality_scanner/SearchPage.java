package sk.lacko.reality_scanner;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Scope("prototype")
public class SearchPage extends APage {

    public SearchPage(HtmlPage htmlPage) {
        super(htmlPage);
    }

    @Override
    public List<IPage> process() {
        try {
            HtmlForm submitForm = htmlPage.getForms().iterator().next();
            htmlPage.executeJavaScript("$(\"input[name*='stateName']\" ).val(\"Slovenská republika\");");
            htmlPage.executeJavaScript("$(\"input[name*='cityName']\" ).val(\"Handlová (Prievidza)\");");
            htmlPage.executeJavaScript("$(\"input[name*='propertyType']\" ).val(\"OBJECTS_FOR_LIVING_AND_RECREATION\");");
            htmlPage.executeJavaScript("$(\"input[name*='transactionType']\" ).val(\"SALE\");");
            final HtmlSubmitInput submitButton = (HtmlSubmitInput) submitForm.getByXPath("//input[@type='submit' and @class='form-button search-button']").get(0);

            return Arrays.asList(new PagingPage((HtmlPage) submitButton.click()));
        }catch (IOException e){

        }
        return new ArrayList<>();
    }
}
