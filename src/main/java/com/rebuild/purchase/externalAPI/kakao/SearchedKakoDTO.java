package com.rebuild.purchase.externalAPI.kakao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchedKakoDTO {
    String resultCode;
    String resultMessae;
    List<SearchedKakaoBookDTO> documents;
    KakaoMeta meta;
}
