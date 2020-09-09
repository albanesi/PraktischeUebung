package ch.noseryoung.uk.domainModels.auction;

import java.util.List;

public interface AuctionService {

    //create a auction and return it
    Auction create(Auction auction);

    //find all auctions and return them
    List<Auction> findAll();

    List<Auction> getBetween(int n1,int n2);

    //find a auction by its id and return it
    Auction findById(int id);

    //update a auction by its id and properties then return it
    Auction updateById(int id, Auction auction);

    //delete a auction by its id
    void deleteById(int id);

}
