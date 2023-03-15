package com.example.listofemployees.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
fun ErrorScreen() =
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen._16dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(dimensionResource(id = R.dimen._56dp)),
            painter = painterResource(id = R.drawable.ic_flying_saucer),
            contentDescription = stringResource(
                id = R.string.empty
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8dp)))
        Text(
            text = stringResource(id = R.string.some_superintelligence_broke_everything),
            color = colorResource(id = R.color.black_dark),
            fontSize = dimensionResource(id = R.dimen._17sp).value.sp,
            fontFamily = FontFamily(Font(R.font.inter_semi_bold))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._12dp)))
        Text(
            text = stringResource(id = R.string.we_will_try_to_fix_it_quickly),
            color = colorResource(id = R.color.gray_darker),
            fontSize = dimensionResource(id = R.dimen._16sp).value.sp,
            fontFamily = FontFamily(Font(R.font.inter_regular))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._12dp)))
        Text(
            text = stringResource(id = R.string.try_again),
            color = colorResource(id = R.color.purple),
            fontSize = dimensionResource(id = R.dimen._16sp).value.sp,
            fontFamily = FontFamily(Font(R.font.inter_semi_bold))
        )
    }

@Composable
@Preview
fun ErrorScreenPreview() =
    ErrorScreen()