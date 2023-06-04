package com.rebuild.book.domain;

import com.rebuild.book.application.dto.FindBookDetailReqServiceDto;
import com.rebuild.book.domain.dto.PaginationRes;

public interface BookCustomRepository {
    public PaginationRes findBooksByDetailConditions(FindBookDetailReqServiceDto findBookDetailReqServiceDto);
}
