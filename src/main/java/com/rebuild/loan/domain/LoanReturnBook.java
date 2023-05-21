package com.rebuild.loan.domain;

import com.rebuild.Utils.DateUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Entity
@Getter
@Table(name="CC_LOAN_TABLE")
@RequiredArgsConstructor
public class LoanReturnBook {

//    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="loan_id")
    private Long loanId;

    private String dt_loan;

    private String tm_loan;

    private String dt_return;

    private String tm_return;

    private Long bookId;

    private Long memberId;

    private int state ;

    private int extension;
    
    public LoanReturnBook(Long memberId, Long bookId){
        setMemberId(memberId);
        setBookId(bookId);
        setDt_loan(DateUtils.getNowDate());
        setTm_loan(DateUtils.getNowTime());
    }
    private void setMemberId(Long memberId){
        this.memberId=memberId; //컨트롤러 단에서 새로 생성(불변타입 사용),불변 객체는 참조 투명성과 스레드에 안전한 특징을 갖고 있다.
    }
    private void setBookId(Long bookId){
        this.bookId=bookId;
    }
    private void setDt_loan(String dt_loan){
        this.dt_loan=dt_loan;
    }
    private void setTm_loan(String tm_loan){
        this.tm_loan=tm_loan;
    }

//    @Builder
//    public LoanReturnBook(MemberId memberId,BookId bookId){
//        this.memberId=memberId;
//        this.bookId=bookId;
//        this.dt_loan= DateUtils.getNowDate();
//        this.tm_loan=DateUtils.getNowTime();
//    }
}
