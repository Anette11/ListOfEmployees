package com.example.listofemployees.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.listofemployees.R

@Composable
fun SortBottomSheet(
    onSortAlphabeticallyClick: () -> Unit,
    isSortAlphabeticallySelected: Boolean,
    onSortByBirthdayClick: () -> Unit,
    isSortByBirthdaySelected: Boolean
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(id = R.dimen._8dp))
        .clip(
            RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen._20dp),
                topEnd = dimensionResource(id = R.dimen._20dp)
            )
        )
        .background(color = colorResource(id = R.color.white)),
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._12dp)))
    Divider(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen._56dp))
            .height(dimensionResource(id = R.dimen._4dp))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen._4dp))),
        color = colorResource(id = R.color.gray_dark)
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._12dp)))
    Text(
        text = stringResource(id = R.string.sort),
        fontFamily = FontFamily(Font(R.font.inter_semi_bold)),
        fontSize = dimensionResource(id = R.dimen._20sp).value.sp,
        color = colorResource(id = R.color.black_dark)
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._35dp)))
    IconAndTextBottomSheet(
        text = stringResource(id = R.string.sort_alphabetically),
        onIconClick = onSortAlphabeticallyClick,
        isSelected = isSortAlphabeticallySelected
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._40dp)))
    IconAndTextBottomSheet(
        text = stringResource(id = R.string.sort_by_birthday),
        onIconClick = onSortByBirthdayClick,
        isSelected = isSortByBirthdaySelected
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._55dp)))
}

@Composable
@Preview
fun SortBottomSheetPreview() =
    SortBottomSheet(
        onSortAlphabeticallyClick = {},
        isSortAlphabeticallySelected = true,
        onSortByBirthdayClick = {},
        isSortByBirthdaySelected = false
    )