package sk.lacko.reality_scanner;

import lombok.Data;

import java.util.Map;

@Data
public class PageInfo {
    private String name;
    private String description;
    private String price;
    private String priceBase;
    private String subType;
    /**
     * size in m2
     */
    private String size;
    private Address address;
    private String type;
    private String imageUrl;
    private String url;

    private String detailedDescription;

    private Map<String, String> additionalInfo;

}
