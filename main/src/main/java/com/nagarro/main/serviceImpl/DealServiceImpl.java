package com.nagarro.main.serviceImpl;

import com.nagarro.main.dto.*;
import com.nagarro.main.service.DealService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DealServiceImpl implements DealService {

    public static final String CATEGORY_NAME = "categoryName";
    private final WebClient amazonWebClient;
    private final WebClient ebayWebClient;
    private final WebClient walmartWebClient;

    public DealServiceImpl(WebClient amazonWebClient, WebClient ebayWebClient, WebClient walmartWebClient) {
        this.amazonWebClient = amazonWebClient;
        this.ebayWebClient = ebayWebClient;
        this.walmartWebClient = walmartWebClient;
    }

    public Mono<DealResponse> getDeals(String categoryName) {
        CompletableFuture<DealItemsResponse> amazonFuture = amazonWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/backendserver1/amazon/deals")
                        .queryParam(CATEGORY_NAME, categoryName)
                        .build())
                .retrieve()
                .bodyToMono(DealItemsResponse.class)
                .toFuture();

        CompletableFuture<DealItemsResponse> ebayFuture = ebayWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/backendserver2/ebay/deals")
                        .queryParam(CATEGORY_NAME, categoryName)
                        .build())
                .retrieve()
                .bodyToMono(DealItemsResponse.class)
                .toFuture();

        CompletableFuture<DealItemsResponse> walmartFuture = walmartWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/backendserver3/walmart/deals")
                        .queryParam(CATEGORY_NAME, categoryName)
                        .build())
                .retrieve()
                .bodyToMono(DealItemsResponse.class)
                .toFuture();

        return Mono.zip(Mono.fromFuture(amazonFuture), Mono.fromFuture(ebayFuture), Mono.fromFuture(walmartFuture))
                .map(deals -> {
                    DealItemsResponse amazonDeals = deals.getT1();
                    DealItemsResponse ebayDeals = deals.getT2();
                    DealItemsResponse walmartDeals = deals.getT3();


                    List<DealItem> allDeals = new ArrayList<>();
                    allDeals.addAll(amazonDeals.getDealItems());
                    allDeals.addAll(ebayDeals.getDealItems());
                    allDeals.addAll(walmartDeals.getDealItems());


                    List<DealItem> filteredDeals = allDeals.stream()
                            .filter(dealItem -> dealItem.getStock() > 0)
                            .filter(dealItem -> LocalDateTime.now().isAfter(dealItem.getDealStartDate())
                                    && LocalDateTime.now().isBefore(dealItem.getDealEndDate()))
                            .collect(Collectors.toList());


                    filteredDeals.sort(Comparator.comparing(DealItem::getDiscountPercentage).reversed()
                            .thenComparing(DealItem::getPriceValue));

                    List<DealItemDTO> dealItemDTOS=new ArrayList<>();
                    for(DealItem d:filteredDeals){
                        DealItemDTO dealItemDTO = getDealItemDTO(d);
                        dealItemDTOS.add(dealItemDTO);
                    }

                    return new DealResponse(categoryName, dealItemDTOS);
                });
    }

    private static DealItemDTO getDealItemDTO(DealItem d) {
        DealItemDTO dealItemDTO=new DealItemDTO();

        dealItemDTO.setItemId(d.getItemId());
        dealItemDTO.setImage(new Image(d.getImageUrl()));
        dealItemDTO.setBrand(d.getBrand());
        dealItemDTO.setPrice(new Price(d.getPriceValue(), d.getPriceCurrency()));
        dealItemDTO.setProductTitle(d.getProductTitle());
        dealItemDTO.setSize(d.getSize());
        dealItemDTO.setMarketingPrice(new MarketingPrice(new Price(d.getPriceValue(), d.getPriceCurrency()), d.getDiscountPercentage(),null,""));
        dealItemDTO.setStock(d.getStock());
        dealItemDTO.setDealStartDate(d.getDealStartDate());
        dealItemDTO.setDealEndDate(d.getDealEndDate());
        return dealItemDTO;
    }
}

