package com.example.listofemployees.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.listofemployees.R

@Composable
fun DividerSmall() =
    Divider(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen._72dp))
            .height(dimensionResource(id = R.dimen._1dp)),
        color = colorResource(id = R.color.gray_dark)
    )

@Composable
@Preview
fun DividerSmallPreview() =
    DividerSmall()
