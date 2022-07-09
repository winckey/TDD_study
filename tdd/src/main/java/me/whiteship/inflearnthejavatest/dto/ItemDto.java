package me.whiteship.inflearnthejavatest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private String itemContent;


    private List<OptionDto> options;


    private int quantity;


    private int price;


}
