package ch.noseryoung.uk.domainModels.auction.dto;

public class AuctionDTO {

    //Fields
    private int id;

    private String name;

    private String reason;

    private Float price;


    // Standard empty constructor
    public AuctionDTO() {
    }




    // Getters and setters
    public int getId() {
        return id;
    }

    public AuctionDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuctionDTO setName(String name) {
        this.name = name;
        return this;
    }
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
