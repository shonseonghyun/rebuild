package com.rebuild.book.domain;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;


@Entity
@Table(name="CC_BOOK_TABLE")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Access(AccessType.FIELD)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키  생성을 데이터베이스에 위임(데이터베이스에 의존적), Entity 등록 시 DB에서 AUTO_INCREMENT하여 PK생성 후 INSERT
    private Long bookId;

    private String title;

    private String dt_reg;

    private String author;

    private String content;

    private String publisher;

    @Convert(converter = BookStateConverter.class)
    @Column(name = "state")
    private BookState bookState;

    private String isbn;

    private String dt_pub;

    /*1번 방식*/
//    public boolean canLoan(){
//        System.out.println("대출가능 여부 확인 시작");
//        return this.bookState.equals(BookState.LOANING_AVAILABLE);
//    }
//
//    public void changeBookState(BookState bookState){
//        this.bookState = bookState;
//    }
    /*2번 방식*/

    public void loanBook(){
        checkLoanBookPossible();
        changeBookState(BookState.LOANING_NOT_AVAILABLE);
    }
    private void checkLoanBookPossible(){ //반납까지 생각한다면..? boolean타입이 재사용성 높을것으로 보임. 추후 바꿔보자
        if( !(this.bookState.equals(BookState.LOANING_AVAILABLE))) {
            throw new NoSuchElementException("해당 도서 대여불가");
        }
    }
    private void changeBookState(BookState bookState){
        this.bookState=bookState;
    }


    public void returnBook(){
        changeBookState(BookState.LOANING_AVAILABLE);
    }

}
