package ch.noseryoung.uk.domainModels.auction.integrationstest;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuctionIntegrationTest {

    @Autowired
    private MockMvc MockMvc;
    @Autowired
    private AuctionRepository auctionRepository;


    @Test
    public void findAll_requestsAllAuctions_retrunsAllAuctions() throws Exception {
        Auction a1 = new Auction().setName("a").setPrice(12).setReason("idk");
        Auction a2 = new Auction().setName("b").setPrice(13).setReason("idk");
        a1= auctionRepository.save(a1);
        a2 =auctionRepository.save(a2);

        MockMvc.perform(
                MockMvcRequestBuilders.get("/auctions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(a1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(a2.getId()));
    }

}
