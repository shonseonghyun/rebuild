package com.rebuild.book.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rebuild.book.application.dto.FindBookDetailReqServiceDto;
import com.rebuild.book.domain.BookCustomRepository;
import com.rebuild.book.domain.QBook;
import com.rebuild.book.domain.dto.FindBooksByDetailConditionsRes;
import com.rebuild.book.domain.dto.PaginationRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final QBook book = QBook.book;

    /* offset 상세조회 */
    /* 기본 dT_pub기준 최신 정렬 */
    @Override
    public PaginationRes findBooksByDetailConditions(FindBookDetailReqServiceDto findBookDetailReqServiceDto) {
                JPAQuery<FindBooksByDetailConditionsRes> query = queryFactory.select(Projections.fields(
                        FindBooksByDetailConditionsRes.class,book.bookId
                        ,book.title
                        ,book.author
                        ,book.publisher
                        ,book.bookState.as("state")
                        ,book.dt_pub
                         )
                        )
                        .from(book)
                    .where(detailConditions(findBookDetailReqServiceDto))
                    .orderBy(getOrder(findBookDetailReqServiceDto.getSort()).desc())
                    .offset(Long.parseLong(findBookDetailReqServiceDto.getPage())-1)
                    .limit(Long.parseLong(findBookDetailReqServiceDto.getSize()))
                     ;
                List<FindBooksByDetailConditionsRes> list = query.fetch();
                Long totalCount = query.fetchCount();
                return new PaginationRes(list,totalCount);
    }

    private BooleanBuilder detailConditions(FindBookDetailReqServiceDto dto){
        return containAuthor(dto.getAuthor()).
                and(containTitle(dto.getTitle())).
                and(containIsbn(dto.getIsbn())).
                and(containPub(dto.getPub())).
                and(containDate(dto.getStartDate(),dto.getEndDate()));
    }

    private BooleanBuilder containTitle(String title){
        return StringUtils.hasText(title) ? new BooleanBuilder(book.title.contains(title)) : new BooleanBuilder();
    }
    private BooleanBuilder containAuthor(String author){
        return StringUtils.hasText(author) ? new BooleanBuilder(book.author.contains(author)) : new BooleanBuilder();
    }
    private BooleanBuilder containPub(String pub){
        return StringUtils.hasText(pub) ? new BooleanBuilder(book.publisher.contains(pub)) : new BooleanBuilder();
    }
    private BooleanBuilder containIsbn(String isbn){
        return StringUtils.hasText(isbn) ? new BooleanBuilder(book.isbn.contains(isbn)) : new BooleanBuilder();
    }
    private BooleanBuilder containDate(String startDate, String endDate){
        return (StringUtils.hasText(startDate) && StringUtils.hasText(endDate))  ? new BooleanBuilder(book.dt_pub.between(startDate,endDate)) : new BooleanBuilder();
    }
    private StringPath getOrder(String order){
        if(order==null || order.isEmpty()) return book.dt_pub;

        switch (order){
            case "title":
                return book.title;
            default:
                return book.dt_pub;
        }
    }

}
