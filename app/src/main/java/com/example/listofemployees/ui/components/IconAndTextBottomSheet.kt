package com.example.listofemployees.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.listofemployees.R

@Composable
fun IconAndTextBottomSheet(
    text: String,
    onIconClick: () -> Unit,
    isSelected: Boolean
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(id = R.dimen._18dp)),
    verticalAlignment = Alignment.CenterVertically
) {
    IconButton(
        onClick = onIconClick,
        modifier = Modifier.size(dimensionResource(id = R.dimen._24dp))
    ) {
        Icon(
            painter = painterResource(
                id = if (isSelected) R.drawable.ic_selected
                else R.drawable.ic_unselected
            ),
            contentDescription = stringResource(id = R.string.empty),
            tint = colorResource(id = R.color.purple)
        )
    }
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._14dp)))
    Text(
        text = text,
        color = colorResource(id = R.color.black_dark),
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = dimensionResource(id = R.dimen._16sp).value.sp
    )
}

@Composable
@Preview
fun IconAndTextBottomSheetPreview() =
    IconAndTextBottomSheet(
        text = stringResource(id = R.string.sort_alphabetically),
        onIconClick = {},
        isSelected = true
    )