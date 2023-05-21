package com.rebuild.loan.application;

import com.rebuild.book.domain.Book;
import com.rebuild.book.domain.BookRepository;
import com.rebuild.loan.application.dto.LoanBookReqServiceDto;
import com.rebuild.loan.application.dto.ReturnBookReqServiceDto;
import com.rebuild.loan.domain.LoanReturnBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoanReturnService {

    private final LoanReturnBookRepository loanReturnBookRepository;
    private final BookRepository bookRepository;

    /*트랜잭션 해당 위치 사유
     1. 트랜잭션의 범위는 최소화가 가장 좋다
     => 도서를 빌린다면 도서 상태변경, 대여목록 저장 이 같이 이루어지는 구조, 즉 이 범위가 가장 최소
     
     2.
     */
    @Transactional
    public void loanBook(LoanBookReqServiceDto loanBookReqServiceDto){
        Book book = bookRepository.findById(loanBookReqServiceDto.getBookId())
                .orElseThrow(()-> new NoSuchElementException("해당 도서가 존재하지 않습니다."));

        /*1번 방식*/
        /* <단점>
            1.책상태를 확인하고 대여불가 상태로 만드는 로직이 응용서비스 계층에 노출됨
            2.동일한 검사 로직이 여러 Application 서비스에 중복 구현될 가능성 높아짐
        */
//        if(book.canLoan()){
//            rentReturnBookRepository.save(loanBookRequest.toEntity());
//            book.changeBookState(BookState.LOANING_NOT_AVAILABLE);
//            System.out.println("대출 성공");
//        }
//        throw new NoSuchElementException(loanBookRequest.getBookId()+ " 도서 대여 불가합니다.");


        /*2번 방식*/
        /* <장점>
             1. 책상태를 확인하고 대여불가 상태로 만드는 로직이  Book루트 엔티티에 위치함
             2. 동일한 검사 로직 중복 제거 가능
             3. 앤티티 필드값 변경은 도메인 객체 내에서 이루어지므로 안정성 up
             4. 한 응용계층에서 두 애그리거트 수정하도록 구현함 -> 책에 있는 지향하는 내용
        */
        book.loan(); //도서 상태 검사 및 도서 상태 변경
        loanReturnBookRepository.save(loanBookReqServiceDto.toEntity()); //대여목록 생성
    }


    public void returnBook(ReturnBookReqServiceDto returnBookReqServiceDto) {
        //해당 도서 조회하지 않은경우 -> 에러 리턴\


    }

//    public List<CurrentLoanBookListResponse> showCurrentLoanBookList(Long memberId) {
//        List<LoanReturnBook> list = loanReturnBookRepository.findByMemberIdOrderByDt_loanDescAndNotReturn(memberId);
//        List<CurrentLoanBookListResponse> resList =  new ArrayList<>();
//        for(LoanReturnBook book : list){
//            resList.add(new CurrentLoanBookListResponse(book));
//        }
//        return resList;
//    }

}
