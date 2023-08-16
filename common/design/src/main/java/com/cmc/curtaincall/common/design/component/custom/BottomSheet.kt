package com.cmc.curtaincall.common.design.component.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog

enum class SortType(val value: String) {
    STAR("별점순"), END("종료 임박순"), KOREAN("가나다순")
}

@Composable
fun SelectSortTypeBottomSheet(
    sortType: SortType,
    onSelectSortType: (SortType) -> Unit
) {
    BottomSheetDialog(onDismissRequest = { }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(196.dp)
                .background(White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .padding(vertical = 15.dp, horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelectSortType(SortType.STAR) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = SortType.STAR.value,
                    modifier = Modifier.weight(1f),
                    color = if (sortType == SortType.STAR) Me_Pink else Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                if (sortType == SortType.STAR) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Me_Pink
                    )
                }
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelectSortType(SortType.END) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = SortType.END.value,
                    modifier = Modifier.weight(1f),
                    color = if (sortType == SortType.END) Me_Pink else Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                if (sortType == SortType.END) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Me_Pink
                    )
                }
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelectSortType(SortType.KOREAN) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = SortType.KOREAN.value,
                    modifier = Modifier.weight(1f),
                    color = if (sortType == SortType.KOREAN) Me_Pink else Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                if (sortType == SortType.KOREAN) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Me_Pink
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectSortTypeBottomSheetPreview() {
    SelectSortTypeBottomSheet(
        sortType = SortType.STAR,
        onSelectSortType = {}
    )
}
