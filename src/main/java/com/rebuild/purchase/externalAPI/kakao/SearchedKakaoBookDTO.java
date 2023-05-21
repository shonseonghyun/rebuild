package com.rebuild.purchase.externalAPI.kakao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchedKakaoBookDTO {
    List<String> authors;
    String contents;
    String datetime;
    String isbn;
    String price;
    String publisher;
    String sale_price;
    String status;
    String thumbnail;
    String title;

}
