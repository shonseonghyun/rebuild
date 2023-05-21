package com.rebuild.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookPurchaseRepository extends JpaRepository<BookApplication,Long> {
    @Query(value="select count(1) from cc_book_purchase where isbn13= :isbn13" , nativeQuery = true)
    int findByIsbn13(@Param("isbn13") String isbn13);

    @Query(value="select * from cc_book_purchase where member_id= :memberId" , nativeQuery = true)
    List<BookApplication> findAllBookReqListByMemberId(@Param("memberId") String memberId);
}
