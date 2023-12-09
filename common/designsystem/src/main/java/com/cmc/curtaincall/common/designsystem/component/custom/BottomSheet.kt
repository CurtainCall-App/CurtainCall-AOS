package com.cmc.curtaincall.common.designsystem.component.custom

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog

enum class SortType(
    val value: String,
    val code: String
) {
    STAR("별점순", "reviewGradeAvg,desc"),
    END("종료 임박순", "endDate"),
    KOREAN("가나다순", "name")
}

@Composable
fun SelectSortTypeBottomSheet(
    sortType: SortType,
    onSelectSortType: (SortType) -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    BottomSheetDialog(onDismissRequest = onDismissRequest) {
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

@Composable
fun EditBottomSheet(
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    BottomSheetDialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(187.dp)
                .background(White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp, bottom = 35.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(Cultured, RoundedCornerShape(12.dp))
                    .clickable { onEdit() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_correct),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = stringResource(R.string.mypage_writing_edit),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Chinese_Black,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(Cultured, RoundedCornerShape(12.dp))
                    .clickable { onDelete() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = stringResource(R.string.mypage_writing_remove),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Chinese_Black,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
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
