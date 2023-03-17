package com.example.listofemployees.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.data.remote.Item
import com.example.listofemployees.R
import com.example.listofemployees.util.toStringDate

@Composable
fun OneItem(
    showBirthday: Boolean,
    user: Item,
    onUserClick: (Item) -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = dimensionResource(id = R.dimen._16dp),
            vertical = dimensionResource(id = R.dimen._6dp)
        )
        .clickable { onUserClick(user) },
    verticalAlignment = Alignment.CenterVertically
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(user.avatarUrl)
            .crossfade(true)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .build()
    )
    Image(
        modifier = Modifier
            .size(dimensionResource(id = R.dimen._72dp))
            .clip(CircleShape),
        painter = painter,
        contentDescription = stringResource(id = R.string.empty),
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._16dp)))
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.black_dark),
                        fontSize = dimensionResource(id = R.dimen._16sp).value.sp,
                        fontFamily = FontFamily(Font(R.font.inter_medium))
                    )
                ) {
                    append("${user.firstName} ${user.lastName}")
                }
                append(stringResource(id = R.string.space))
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.gray_darker),
                        fontSize = dimensionResource(id = R.dimen._14sp).value.sp,
                        fontFamily = FontFamily(Font(R.font.inter_medium))
                    )
                ) {
                    append(user.userTag.lowercase())
                }
            }
        )
        Text(
            text = user.position,
            color = colorResource(id = R.color.gray_darkest),
            fontSize = dimensionResource(id = R.dimen._13sp).value.sp,
            fontFamily = FontFamily(Font(R.font.inter_regular))
        )
    }
    if (showBirthday) {
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._16dp)))
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = user.birthday.toStringDate(),
                color = colorResource(id = R.color.gray_darkest),
                fontSize = dimensionResource(id = R.dimen._15sp).value.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular))
            )
        }
    }
}

@Composable
@Preview
fun OneItemPreview() =
    OneItem(
        showBirthday = true,
        onUserClick = {},
        user = Item(
            avatarUrl = stringResource(id = R.string.name),
            birthday = stringResource(id = R.string.name),
            department = stringResource(id = R.string.name),
            firstName = stringResource(id = R.string.name),
            id = stringResource(id = R.string.name),
            lastName = stringResource(id = R.string.name),
            phone = stringResource(id = R.string.name),
            position = stringResource(id = R.string.name),
            userTag = stringResource(id = R.string.name)
        )
    )