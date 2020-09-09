package ch.noseryoung.uk.domainModels.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuctionServiceImpl implements AuctionService {


    private AuctionRepository auctionRepository;

    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Auction create(Auction auction) {
        auctionRepository.save(auction);
        return auction;
    }

    //The logic for retrieving all auctions
    @Override
    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    //The logic for retrieving one auction by its id
    @Override
    public Auction findById(int id) {
        Optional<Auction> optional = auctionRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
        }
    }

    //The logic for updating a auction by its id and properties
    @Override
    public Auction updateById(int id, Auction auction) {
        if (auctionRepository.existsById(id)) {
            auctionRepository.save(auction);
        } else {
            throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
        }
        return auction;
    }

    @Override
    public List<Auction> getBetween(int n1, int n2){
        //return  auctionRepository.findAll().stream().filter(n-> n.getPrice()>n1 && n.getPrice()<n2 ).collect(Collectors.toList());
        List<Auction> test = auctionRepository.findAll();
        return test;
    }

    //The logic for deleting a auction by its id
    @Override
    public void deleteById(int id) {
        auctionRepository.deleteById(id);
        System.out.println("The delete method has been called with the id: " + id);
    }
}

  /*Auction b = new Auction().setId(1).setName("hallo");
        List<Auction> list = new LinkedList<Auction>();
        list.add(b);*/