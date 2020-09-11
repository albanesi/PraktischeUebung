package ch.noseryoung.uk.domainModels.auction;

import javax.persistence.*;

@Entity

@Table(name = "auction")
public class Auction {

    // Regular attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //its the name
    @Column(nullable = false)
    private String name;

    private String reason;

    @Column(name = "price")
    private float price;


    // Standard empty constructor
    public Auction() {
    }

    public float getPrice() {
        return price;
    }

    public Auction setPrice(float price) {
        this.price = price;
        return this;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public Auction setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Auction setName(String name) {
        this.name = name;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Auction setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
