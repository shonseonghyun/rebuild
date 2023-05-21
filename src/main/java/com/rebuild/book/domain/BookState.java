package com.rebuild.book.domain;


import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BookState {
    LOANING_AVAILABLE("0","대출 가능"),
    LOANING_NOT_AVAILABLE("1","대출 불가");

    private String state;
    private String desc;

    BookState(String state,String desc) {
        this.state=state;
        this.desc = desc;
    }


//    as-is)만약 상태코드가 추가된다면 어떻게 해야할까...?
//    static String convertStateToDesc(String state){
//        if(state == "0"){
//            BookState state = BookState.RENTING_NOT_AVAILABLE;
//            return state.getDesc();
//        } else if (state == "1") {
//            BookState state = BookState.RENTING_AVAILABLE;
//            return state.getDesc();
////        }
////        else{
////            return "에러";
////        }
//    }


//    to-be)
    public static BookState ofBookState(String state){
        return Arrays.stream(BookState.values())
                .filter(v->v.getState().equals(state))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태값 입니다." + state ));
    }
}
