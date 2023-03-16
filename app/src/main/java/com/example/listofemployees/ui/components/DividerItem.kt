package com.example.listofemployees.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.listofemployees.R

@Composable
fun DividerItem() =
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._16dp)),
        color = colorResource(id = R.color.gray_light),
        thickness = dimensionResource(id = R.dimen._0_5dp)
    )

@Composable
@Preview
fun DividerItemPreview() =
    DividerItem()