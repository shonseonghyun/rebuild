package com.rebuild.purchase.externalAPI.aladin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
public class SearchedAladinBookDTO {
    @NonNull
    String Title;
    String Author;
    String PubDate;
    String Description;
    String Publisher;
    String isbn13;
    String cover;
}
