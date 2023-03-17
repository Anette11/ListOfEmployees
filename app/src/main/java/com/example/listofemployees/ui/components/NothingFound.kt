package com.example.listofemployees.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun NothingFound() =
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen._80dp)))
        Image(
            painter = painterResource(id = R.drawable.ic_search_glass),
            contentDescription = stringResource(id = R.string.empty),
            modifier = Modifier.size(dimensionResource(id = R.dimen._56dp))
        )
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen._8dp)))
        Text(
            text = stringResource(id = R.string.nobody_was_found),
            color = colorResource(id = R.color.black_dark),
            fontFamily = FontFamily(Font(R.font.inter_semi_bold)),
            fontSize = dimensionResource(id = R.dimen._17sp).value.sp
        )
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen._12dp)))
        Text(
            text = stringResource(id = R.string.try_correct_request),
            color = colorResource(id = R.color.gray_darker),
            fontFamily = FontFamily(Font(R.font.inter_regular)),
            fontSize = dimensionResource(id = R.dimen._16sp).value.sp
        )
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen._24dp)))
    }

@Composable
@Preview
fun NothingFoundPreview() =
    NothingFound()