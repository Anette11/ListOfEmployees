package com.example.listofemployees.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.example.listofemployees.R

@Composable
fun ShimmerItem() {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = linearGradient(
        colors = listOf(
            colorResource(id = R.color.gray_gradient_start),
            colorResource(id = R.color.gray_gradient_end)
        ),
        start = Offset(0f, 0f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen._16dp),
                vertical = dimensionResource(id = R.dimen._6dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen._72dp))
                .clip(CircleShape)
                .background(brush = brush)
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._16dp)))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._144dp))
                    .height(dimensionResource(id = R.dimen._16dp))
                    .clip(RoundedCornerShape(corner = CornerSize(dimensionResource(id = R.dimen._50dp))))
                    .background(brush = brush)
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._6dp)))
            Spacer(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen._80dp))
                    .height(dimensionResource(id = R.dimen._12dp))
                    .clip(RoundedCornerShape(corner = CornerSize(dimensionResource(id = R.dimen._50dp))))
                    .background(brush = brush)
            )
        }
    }
}