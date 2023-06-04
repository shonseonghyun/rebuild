package com.rebuild.book.presentation.dto;

import com.rebuild.book.application.dto.FindBookDetailReqServiceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindBookDetailsReqDto {
    public String title; //제목
    public String author; //저자
    public String pub; //출판사
    public String isbn; //isbn
    public String startDate; //출판 시작일
    public String endDate; //출판 종룔일
    public String page; //페이지 번호

    public String size; //페이지 당 노출할 개수
    public String sort;


    public FindBookDetailReqServiceDto toServiceDto(){
        return FindBookDetailReqServiceDto.builder()
                .title(this.title)
                .author(this.author)
                .pub(this.pub)
                .isbn(this.isbn)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .page(this.page)
                .size(this.size)
                .sort(this.sort)
                .build();
    }
}
