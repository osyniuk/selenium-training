package pageObject.model;

public class Product {

    private String dateValidFrom;
    private String dateValidTo;
    private String code;
    private String name;
    private String sku;
    private String gtin;
    private String taric;
    private String quantity;
    private String weight;
    private String dimX;
    private String dimY;
    private String dimZ;
    private int statusId;
    private String fileName;
    private int manId;
    private String keywords;
    private String shortDescription;
    private String description;
    private String attributes;
    private String headTitle;
    private String metaDescription;
    private String purchasePrice;
    private String currencyCode;
    private String USDPrice;
    private String EURPrice;

public static Builder newEntity() {
    return new Product().new Builder();
}
    public String getDateValidFrom() {
        return dateValidFrom;
    }

    public String getDateValidTo() {
        return dateValidTo;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public String getGtin() {
        return gtin;
    }

    public String getTaric() {
        return taric;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getWeight() {
        return weight;
    }

    public String getDimX() {
        return dimX;
    }

    public String getDimY() {
        return dimY;
    }

    public String getDimZ() {
        return dimZ;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getFileName() {
        return fileName;
    }

    public int getManId() {
        return manId;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getAttributes() {
        return attributes;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getUSDPrice() {
        return USDPrice;
    }

    public String getEURPrice() {
        return EURPrice;
    }

    public class Builder {
    private Builder() {}
    public Builder withDateValidFrom (String dateValidFrom) {Product.this.dateValidFrom=dateValidFrom; return this;}
    public Builder withDateValidTo (String dateValidTo) {Product.this.dateValidTo=dateValidTo; return this;}
    public Builder withCode (String code) {Product.this.code=code; return this;}
    public Builder withName (String name) {Product.this.name=name; return this;}
    public Builder withSku (String sku) {Product.this.sku=sku; return this;}
    public Builder withGtin (String gtin) {Product.this.gtin=gtin; return this;}
    public Builder withTaric (String taric) {Product.this.taric=taric; return this;}
    public Builder withQuantity (String quantity) {Product.this.quantity=quantity; return this;}
    public Builder withWeight (String weight) {Product.this.weight=weight; return this;}
    public Builder withDimX (String dimX) {Product.this.dimX=dimX; return this;}
    public Builder withDimY (String dimY) {Product.this.dimY=dimY; return this;}
    public Builder withDimZ (String dimZ) {Product.this.dimZ=dimZ; return this;}
    public Builder withStatusId (int statusId) {Product.this.statusId=statusId; return this;}
    public Builder withFileName (String fileName) {Product.this.fileName=fileName; return this;}
    public Builder withManId (int manId) {Product.this.manId=manId; return this;}
    public Builder withKeywords (String keywords) {Product.this.keywords=keywords; return this;}
    public Builder withShortDescription (String shortDescription) {Product.this.shortDescription=shortDescription; return this;}
    public Builder withDescription (String description) {Product.this.description=description; return this;}
    public Builder withAttributes (String attributes) {Product.this.attributes=attributes; return this;}
    public Builder withHeadTitle (String headTitle) {Product.this.headTitle=headTitle; return this;}
    public Builder withMetaDescription (String metaDescription) {Product.this.metaDescription=metaDescription; return this;}
    public Builder withPurchasePrice (String purchasePrice) {Product.this.purchasePrice=purchasePrice; return this;}
    public Builder withCurrencyCode (String currencyCode) {Product.this.currencyCode=currencyCode; return this;}
    public Builder withUSDPrice (String USDPrice) {Product.this.USDPrice=USDPrice; return this;}
    public Builder withEURPrice (String EURPrice) {Product.this.EURPrice=EURPrice; return this;}



    public Product build() {return Product.this;}
    }
}
