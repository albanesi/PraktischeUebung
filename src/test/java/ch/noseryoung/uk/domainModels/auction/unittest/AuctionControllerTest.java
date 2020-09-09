package ch.noseryoung.uk.domainModels.auction.unittest;

import org.assertj.core.api.Assertions;
import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionService;
import ch.noseryoung.uk.domainModels.auction.dto.AuctionDTO;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpClientErrorException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuctionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuctionService auctionService;
    private Auction testAuction;
    private Auction testAuction2;
    private List<Auction> testAuctionList;

    @BeforeEach
    void setUp() {
        testAuction = new Auction();
        testAuction.setId(1);
        testAuction.setName("car");
        testAuction.setReason("dont know");
        testAuction.setPrice(13);
        testAuction2=new Auction().setId(1).setName("plane").setPrice(133).setReason("idk");
        testAuctionList= new LinkedList<Auction>();
        testAuctionList.add(testAuction);
        testAuctionList.add(testAuction2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void getBetween_requestsAllAuctionsBetweenTwoNumbers_returnsAllAuctions() throws Exception{
        given(auctionService.getBetween(12,144)).willReturn(testAuctionList);
        mvc.perform(
                MockMvcRequestBuilders.get("/auctions/{n1}/{n2}",12,144)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(testAuction.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(testAuction.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reason").value(testAuction.getReason()));

        verify(auctionService,times(1)).getBetween(12,144);
    }
    @Test
    public void findAll_requestAllAuctions_returnsAllAuctions() throws Exception{
        given(auctionService.findAll()).willReturn(testAuctionList);
        mvc.perform(
                MockMvcRequestBuilders.get("/auctions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(testAuction.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(testAuction.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reason").value(testAuction.getReason()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(testAuction2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(testAuction2.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].reason").value(testAuction2.getReason()));

        verify(auctionService,times(1)).findAll();
    }
    @Test
    //@WithMockUser
    public void findByID_requestUserById_returnsUser() throws Exception{
        given(auctionService.findById(anyInt())).will(invocation -> {
            if ("non-existent".equals(invocation.getArgument(0))) throw new Exception();
            return (testAuction);
        });
        mvc.perform(
                MockMvcRequestBuilders.get("/auctions/{id}",testAuction.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testAuction.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testAuction.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reason").value(testAuction.getReason()));

        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(auctionService,times(1)).findById(integerArgumentCaptor.capture());
        Assertions.assertThat(integerArgumentCaptor.getValue()).isEqualTo((testAuction.getId()));
        //Assertions.assertEquals(integerArgumentCaptor.getValue().equals(testAuction.getId()));
    }

}