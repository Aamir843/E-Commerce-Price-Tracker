package com.nagarro.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealResponse {
    private String categoryName;
    private List<DealItemDTO> dealItems;
}

