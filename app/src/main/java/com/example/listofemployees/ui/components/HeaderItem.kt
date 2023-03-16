package com.example.listofemployees.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.listofemployees.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeaderItem(
    onBackClick: () -> Unit,
    image: Any?,
    firstName: String,
    lastName: String,
    userTag: String,
    position: String
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .background(color = colorResource(id = R.color.gray_light))
        .padding(horizontal = dimensionResource(id = R.dimen._16dp))
) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._26dp)))
    Box(modifier = Modifier.fillMaxWidth()) {
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = stringResource(id = R.string.empty),
                    tint = colorResource(id = R.color.black_dark)
                )
            }
        }
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .build()
        )
        Image(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen._12dp))
                .size(dimensionResource(id = R.dimen._104dp))
                .clip(CircleShape)
                .shadow(elevation = dimensionResource(id = R.dimen._8dp))
                .align(Alignment.TopCenter),
            painter = painter,
            contentDescription = stringResource(id = R.string.empty),
            contentScale = ContentScale.Crop
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._24dp)))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.black_dark),
                        fontSize = dimensionResource(id = R.dimen._24sp).value.sp,
                        fontFamily = FontFamily(Font(R.font.inter_bold))
                    )
                ) {
                    append(firstName)
                    append(stringResource(id = R.string.space))
                    append(lastName)
                }
                append(stringResource(id = R.string.space))
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.gray_darker),
                        fontSize = dimensionResource(id = R.dimen._17sp).value.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular))
                    )
                ) {
                    append(userTag)
                }
            }
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._12dp)))
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = position,
        color = colorResource(id = R.color.gray_darkest),
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = dimensionResource(id = R.dimen._13sp).value.sp
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._24dp)))
}

@Composable
@Preview
fun HeaderItemPreview() =
    HeaderItem(
        onBackClick = {},
        image = R.drawable.ic_placeholder,
        firstName = stringResource(id = R.string.name),
        lastName = stringResource(id = R.string.name),
        userTag = stringResource(id = R.string.userTag),
        position = stringResource(id = R.string.position)
    )