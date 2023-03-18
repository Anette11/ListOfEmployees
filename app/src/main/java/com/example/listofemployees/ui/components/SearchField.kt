package com.example.listofemployees.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.*
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
    onMenuClick: () -> Unit,
    onClearClick: () -> Unit,
    isMenuSelected: Boolean
) {
    val localFocusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = dimensionResource(id = R.dimen._16dp),
                    end = dimensionResource(id = R.dimen._16dp),
                    top = dimensionResource(id = R.dimen._18dp),
                    bottom = dimensionResource(id = R.dimen._22dp)
                )
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen._16dp)))
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
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
                        onClick = {},
                        enabled = false
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = stringResource(id = R.string.search_description),
                            tint = colorResource(
                                id = if (isFocused) R.color.black_dark
                                else R.color.gray_dark
                            )
                        )
                    }
                },
                trailingIcon = {
                    when (isFocused || value.isNotEmpty()) {
                        true -> IconButton(
                            onClick = onClearClick
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clear),
                                contentDescription = stringResource(id = R.string.menu_description),
                                tint = colorResource(id = R.color.gray_dark)
                            )
                        }
                        false -> IconButton(
                            onClick = onMenuClick
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_menu),
                                contentDescription = stringResource(id = R.string.menu_description),
                                tint = colorResource(
                                    id = if (isMenuSelected) R.color.purple
                                    else R.color.gray_dark
                                )
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.gray_light),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.black_dark),
                    fontSize = dimensionResource(id = R.dimen._15sp).value.sp,
                    fontFamily = FontFamily(Font(R.font.inter_medium))
                ),
                maxLines = integerResource(id = R.integer._1)
            )
        }
        if (isFocused || value.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.cancel),
                color = colorResource(id = R.color.purple),
                fontFamily = FontFamily(Font(R.font.inter_semi_bold)),
                fontSize = dimensionResource(id = R.dimen._14sp).value.sp,
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen._16dp))
                    .clickable {
                        localFocusManager.clearFocus()
                        onClearClick()
                    }
            )
        }
    }
}

@Composable
@Preview
fun SearchFieldPreview() =
    SearchField(
        value = stringResource(id = R.string.empty),
        onValueChange = {},
        onMenuClick = {},
        onClearClick = {},
        isMenuSelected = true
    )