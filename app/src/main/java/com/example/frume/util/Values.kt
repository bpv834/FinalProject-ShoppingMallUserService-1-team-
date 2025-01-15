package com.example.frume.util

enum class UserInfoType(var number: Int, var str: String) {
    // 주문 내역 및 배송조회
    USER_ORDER_HISTORY_FRAGMENT(0, "주문 내역 및 배송조회"),

    // 주문 상세 내역
    USER_ORDER_DETAIL_FRAGMENT(1, "주문 상세 내역"),

    // 주문 반품 및 취소
    USER_CANCEL_AND_RETURN_FRAGMENT(2, "주문 반품 및 취소"),

    // 회원 정보 관리 및 탈퇴
    USER_INFO_MANAGE_FRAGMENT(3, "회원 정보 관리 및 탈퇴"),

    // 회원 정보 수정
    USER_INFO_MODIFY_FRAGMENT(4, "회원 정보 수정"),

    // 배송지 관리
    USER_ADDRESS_MANAGE_FRAGMENT(5,"배송지 관리")
}