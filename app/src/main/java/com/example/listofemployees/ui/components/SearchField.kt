package com.example.listofemployees.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.listofemployees.R

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onMenuClick: () -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen._16dp),
            end = dimensionResource(id = R.dimen._16dp),
            top = dimensionResource(id = R.dimen._18dp),
            bottom = dimensionResource(id = R.dimen._22dp)
        )
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen._16dp)))
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_field_placeholder),
                color = colorResource(id = R.color.gray_dark),
                fontSize = dimensionResource(id = R.dimen._15sp).value.sp,
                fontFamily = FontFamily(Font(R.font.inter_medium))
            )
        },
        leadingIcon = {
            IconButton(
                onClick = onSearchClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.search_description),
                    tint = colorResource(id = R.color.gray_dark)
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = onMenuClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = stringResource(id = R.string.menu_description),
                    tint = colorResource(id = R.color.gray_dark)
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = R.color.gray_light)
        ),
        textStyle = TextStyle(
            color = colorResource(id = R.color.black_dark),
            fontSize = dimensionResource(id = R.dimen._15sp).value.sp,
            fontFamily = FontFamily(Font(R.font.inter_medium))
        )
    )
}

@Composable
@Preview
fun SearchFieldPreview() =
    SearchField(
        value = "",
        onValueChange = {},
        onSearchClick = {},
        onMenuClick = {}
    )