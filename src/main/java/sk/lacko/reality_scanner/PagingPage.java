package sk.lacko.reality_scanner;

import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PagingPage extends APage implements IPage {
    public PagingPage(HtmlPage htmlPage) {
        super(htmlPage);
    }


    @Override
    public List<IPage> process()  throws IOException {
        htmlPage.getByXPath("//table[@class='search-result-ad-row']");
        htmlPage.getByXPath("//td[@class='picture']/a");
        htmlPage.getByXPath("//table[@class='search-result-ad-row']");

        List<HtmlTable> rows = htmlPage.getByXPath("//table[@class='search-result-ad-row']");

        List<IPage> pagesToAnalyse = new ArrayList<>();

        for (HtmlTable row : rows){
            PageInfo pageInfo = extractData(row);
            HtmlPage page = webClient.getPage(htmlPage.getBaseURI() + pageInfo.getUrl());
            pagesToAnalyse.add(new DetailPage(page,pageInfo));
        }

        return pagesToAnalyse;
    }


    private PageInfo extractData(HtmlElement element){
        String title = ((HtmlAnchor)element.getByXPath("tbody//a").get(0)).getAttribute("title");
        String image = ((HtmlImage)element.getByXPath("tbody//img").get(0)).getAttribute("src");
        String city = ((HtmlSpan)element.getByXPath("tbody//div[@class='city']/span").get(0)).getFirstChild().getNodeValue();
        String subType = ((HtmlSpan)element.getByXPath("tbody//div[@class='type']/span").get(0)).getFirstChild().getNodeValue();
        String size = ((HtmlSpan)element.getByXPath("tbody//div[@class='type']/span[@class='area']").get(0)).getFirstChild().getNodeValue();
        String street = ((HtmlSpan)element.getByXPath("tbody//div[@class='street']/span").get(0)).getFirstChild().getNodeValue();
        String priceTotal = ((HtmlAnchor)element.getByXPath("tbody//div[@class='total']/span").get(0)).getFirstChild().getNodeValue();
        String priceBase = ((HtmlAnchor)element.getByXPath("tbody//div[@class='base']/span").get(0)).getFirstChild().getNodeValue();
        String link = ((HtmlAnchor)element.getByXPath("tbody//a").get(0)).getAttribute("href");

        PageInfo pageInfo = new PageInfo();
        pageInfo.setDescription(title);
        pageInfo.setSubType(subType);
        pageInfo.setImageUrl(image);
        pageInfo.setSize(size);
        pageInfo.setPrice(priceTotal);
        pageInfo.setPriceBase(priceBase);
        pageInfo.setUrl(link);
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        return pageInfo;
    }
}
