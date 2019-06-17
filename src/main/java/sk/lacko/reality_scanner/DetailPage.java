package sk.lacko.reality_scanner;

import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailPage extends APage{

    public DetailPage(HtmlPage htmlPage, PageInfo pageInfo) {
        super(htmlPage);
        this.pageInfo = pageInfo;
    }

    private PageInfo pageInfo;

    @Override
    public List<IPage> process() {

        String detail =  ((DomNode)htmlPage.getByXPath("//div[@class='detail-indent/pre").get(0)).getFirstChild().getNodeValue();
        pageInfo.setDetailedDescription(detail);

        List<HtmlTableRow> additionalDataRows = htmlPage.getByXPath("//table[@class='detail-char']//tr");
        Map<String, String> additionalData = new HashMap<>();

        for(HtmlTableRow additionalDataRow : additionalDataRows){
            DomNodeList<HtmlElement> cells = additionalDataRow.getElementsByTagName("td");
                   String key = cells.get(0).getFirstChild().getNodeValue();
            StringBuilder value = new StringBuilder();
            for (int i = 1; i < cells.size(); i++) {
                value.append(cells.get(i).getFirstChild().getNodeValue());
            }
            additionalData.put(key, value.toString());
        }
        pageInfo.setAdditionalInfo(additionalData);
        System.out.println(pageInfo);

        return new ArrayList<>();
    }
}


