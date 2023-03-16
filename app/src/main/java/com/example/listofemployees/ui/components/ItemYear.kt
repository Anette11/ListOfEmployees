package com.example.listofemployees.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.listofemployees.R

@Composable
fun ItemYear(
    year: String
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen._24dp)),
    verticalAlignment = Alignment.CenterVertically
) {
    DividerSmall()
    Text(
        text = year,
        modifier = Modifier.weight(1f),
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.gray_dark),
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = dimensionResource(id = R.dimen._15sp).value.sp
    )
    DividerSmall()
}

@Composable
@Preview
fun ItemYearPreview() =
    ItemYear(year = stringResource(id = R.string.year_example))