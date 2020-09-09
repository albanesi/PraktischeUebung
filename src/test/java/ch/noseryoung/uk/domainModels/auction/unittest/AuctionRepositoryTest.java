package ch.noseryoung.uk.domainModels.auction.unittest;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
//@TestPropertySource("Classpath:application-test.properties")
public class AuctionRepositoryTest {

    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private TestEntityManager testEntityManager;


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
    public void get() {

    }

}
