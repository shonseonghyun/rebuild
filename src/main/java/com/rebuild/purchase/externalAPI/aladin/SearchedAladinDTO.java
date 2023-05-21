package com.rebuild.purchase.externalAPI.aladin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchedAladinDTO {
    String version;
    String pubDate;
    String query;
    String title;
    List<SearchedAladinBookDTO> item;

}
