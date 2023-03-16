package com.example.listofemployees.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun SnackBarInfo(
    color: Int,
    text: String
) = Row(
    modifier = Modifier
        .padding(
            start = dimensionResource(id = R.dimen._16dp),
            end = dimensionResource(id = R.dimen._16dp),
            bottom = dimensionResource(id = R.dimen._40dp)
        )
        .fillMaxWidth()
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen._12dp)))
        .background(colorResource(id = color))
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen._16dp),
                vertical = dimensionResource(id = R.dimen._14dp)
            ),
        text = text,
        color = colorResource(id = R.color.white),
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = dimensionResource(id = R.dimen._14sp).value.sp
    )
}

@Composable
@Preview
fun SnackBarInfoPreview() =
    SnackBarInfo(
        color = R.color.red,
        text = stringResource(id = R.string.error_something_went_wrong)
    )