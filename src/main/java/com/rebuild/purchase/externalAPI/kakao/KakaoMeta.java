package com.rebuild.purchase.externalAPI.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class KakaoMeta {
    String is_end;
    String pageable_count;
    String total_count;
}
