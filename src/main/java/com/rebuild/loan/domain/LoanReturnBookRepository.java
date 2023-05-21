package com.rebuild.loan.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface LoanReturnBookRepository extends JpaRepository<LoanReturnBook, Long> , JpaSpecificationExecutor<LoanReturnBook> {

//    @Query(value="select * from cc_loan_table where member_id = :memberId and dt_return is null", nativeQuery = true)
//    List<LoanReturnBook> findByMemberIdOrderByDt_loanDescAndNotReturn(@Param("memberId") Long memberId);

    LoanReturnBook findByState(LoanReturnBookSpecification memberId);
}
