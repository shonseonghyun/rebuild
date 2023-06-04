package com.rebuild.purchase;

import com.rebuild.Utils.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="CC_BOOK_PURCHASE")
@AllArgsConstructor
@NoArgsConstructor
public class BookApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키  생성을 데이터베이스에 위임(데이터베이스에 의존적), Entity 등록 시 DB에서 AUTO_INCREMENT하여 PK생성 후 INSERT
    private Long id;

    private String dt_req_purchase;

    private Long member_id;

    private String isbn13;

    private int cl_status;

    private String del_status; //신청취소여부


    @Builder
    public BookApplication(Long member_id, String isbn13){
        this.member_id=member_id;
        this.isbn13= isbn13;
        this.dt_req_purchase = DateUtils.getNowDate();
        this.del_status = "N";
    }

}
