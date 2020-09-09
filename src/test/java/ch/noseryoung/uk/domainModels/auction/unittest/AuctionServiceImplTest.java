package ch.noseryoung.uk.domainModels.auction.unittest;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionRepository;
import ch.noseryoung.uk.domainModels.auction.AuctionService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuctionServiceImplTest {

    @MockBean
    private AuctionRepository auctionRepository;
    @Autowired
    private AuctionService auctionService;
   // @Autowired
    //private AuctionService auctionRepository;

    private Auction testAuction;
    private Auction testAuction2;
    private List<Auction> testAuctionList;

    @BeforeEach
    void setUp() {
        testAuction = new Auction();
        testAuction.setId(1).setName("car").setReason("dont know").setPrice(13);
        testAuction2=new Auction().setId(1).setName("plane").setPrice(133).setReason("idk");
        testAuctionList= new ArrayList<>();
        testAuctionList.add(testAuction);
        testAuctionList.add(testAuction2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void create_createsNewAuction_returnsAuction(){
        given(auctionRepository.findAll()).will(invocation -> (testAuctionList));
        Mockito.when(auctionRepository.findAll()).thenReturn(testAuctionList);

        List<Auction> between= auctionService.getBetween(12,144);
        Assert.assertEquals(between.get(0).getId(),testAuction.getId());
        Assert.assertEquals(between.get(1).getId(),testAuction2.getId());
    }
}