package com.example.listofemployees.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
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
import com.example.listofemployees.util.toBirthday

@Composable
fun BirthdayItem(
    birthday: String,
    age: String
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .background(color = colorResource(id = R.color.white))
        .padding(
            horizontal = dimensionResource(id = R.dimen._16dp),
            vertical = dimensionResource(id = R.dimen._26dp)
        ),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_favourite),
        contentDescription = stringResource(id = R.string.empty),
        tint = colorResource(id = R.color.black_dark)
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._12dp)))
    Text(
        modifier = Modifier.weight(1f),
        text = birthday.toBirthday(),
        color = colorResource(id = R.color.black_dark),
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = dimensionResource(id = R.dimen._16sp).value.sp
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._12dp)))
    Text(
        text = age,
        color = colorResource(id = R.color.gray_darker),
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = dimensionResource(id = R.dimen._16sp).value.sp
    )
}

@Composable
@Preview
fun BirthdayItemPreview() =
    BirthdayItem(
        birthday = stringResource(id = R.string.birthday_test),
        age = stringResource(id = R.string.age_test)
    )