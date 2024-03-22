package com.nagarro.ebay.dto;


import com.nagarro.ebay.model.DealItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DealItemsResponse {

    private String categoryName;
    private List<DealItem> dealItems;

}
