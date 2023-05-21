package com.rebuild.loan.application.dto;

import com.rebuild.loan.domain.LoanReturnBook;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class LoanBookReqServiceDto {
    Long memberId;
    Long bookId;


    /*1번 빌더 방식*/
//    public LoanReturnBook toEntity() {
//        return LoanReturnBook.builder()
//                .memberId(this.memberId)
//                .bookId(this.bookId)
//                .build();
//    }


    /*2번 생성자 방식*/
    /*
    <장점>
       1. set필드 진행 시 필요한 데이터가 올바른지 검증 로직 추가 가능
    <단점>
       1. 코드양이 많아진다.(특히 set함수)
     */
    public LoanReturnBook toEntity() {
        return new LoanReturnBook(memberId,bookId);
    }
}
