package com.rebuild.book.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class FindBookDetailReqServiceDto {
    public String title;
    public String author;
    public String pub;
    public String isbn;
    public String startDate;
    public String endDate;
    public String page;
    public String size;
    public String sort;
}