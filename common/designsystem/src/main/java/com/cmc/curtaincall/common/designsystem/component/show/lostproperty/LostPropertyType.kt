package com.cmc.curtaincall.common.designsystem.component.show.lostproperty

import androidx.annotation.DrawableRes
import com.cmc.curtaincall.common.designsystem.R

enum class LostPropertyType(
    val label: String,
    @DrawableRes val drawableRes: Int,
    val code: String
) {
    TOTAL("전체", R.drawable.ic_total, ""),
    BAG("가방", R.drawable.ic_bag, "BAG"),
    WALLET("지갑", R.drawable.ic_wallet, "WALLET"),
    MONEY("현금", R.drawable.ic_money, "CASH"),
    CARD("카드", R.drawable.ic_card, "CARD"),
    JEWELRY("귀금속", R.drawable.ic_jewelry, "JEWELRY"),
    ELECTRONICS("전자기기", R.drawable.ic_electronics, "ELECTRONIC_EQUIPMENT"),
    BOOKS("도서", R.drawable.ic_books, "BOOK"),
    CLOTHES("의류", R.drawable.ic_clothes, "CLOTHING"),
    ETC("기타", R.drawable.ic_etc, "ETC"),
    ELSE("", R.drawable.ic_error_poster, "")
}
