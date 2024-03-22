package com.nagarro.main.serviceImpl;

import com.nagarro.main.dto.DealItem;
import com.nagarro.main.dto.DealItemsResponse;
import com.nagarro.main.dto.DealResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DealServiceImplTest {

    @Mock
    private WebClient amazonWebClient;

    @Mock
    private WebClient ebayWebClient;

    @Mock
    private WebClient walmartWebClient;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @InjectMocks
    private DealServiceImpl dealService;

    @Test
    public void testGetDeals() {
        String categoryName = "testCategory";

        DealItem dealItem1 = new DealItem();
        // Set properties for dealItem1

        DealItem dealItem2 = new DealItem();
        // Set properties for dealItem2

        DealItemsResponse amazonResponse = new DealItemsResponse();
        amazonResponse.setDealItems(List.of(dealItem1));

        DealItemsResponse ebayResponse = new DealItemsResponse();
        ebayResponse.setDealItems(List.of(dealItem2));

        DealItemsResponse walmartResponse = new DealItemsResponse();
        // Set deal items for walmartResponse

        when(amazonWebClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(DealItemsResponse.class)).thenReturn(Mono.just(amazonResponse));
//        when(amazonResponse.toFuture()).thenReturn(CompletableFuture.completedFuture(amazonResponse));

        when(ebayWebClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(DealItemsResponse.class)).thenReturn(Mono.just(ebayResponse));
//        when(ebayWebClient.toFuture()).thenReturn(CompletableFuture.completedFuture(ebayResponse));

        when(walmartWebClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(DealItemsResponse.class)).thenReturn(Mono.just(walmartResponse));
//        when(walmartWebClient.toFuture()).thenReturn(CompletableFuture.completedFuture(walmartResponse));

        Mono<DealResponse> responseMono = dealService.getDeals(categoryName);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> {
                    // Assert DealResponse properties
                    return true;
                })
                .verifyComplete();
    }
}
