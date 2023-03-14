package com.example.listofemployees.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.listofemployees.R

@Composable
fun Tabs(
    tabIndex: Int,
    selectedTabIndex: Int,
    tabNames: List<String>,
    onTabClick: (Int) -> Unit,
    content: @Composable () -> Unit
) = Column(
    modifier = Modifier.fillMaxWidth()
) {
    ScrollableTabRow(
        backgroundColor = colorResource(id = R.color.white),
        selectedTabIndex = selectedTabIndex,
        edgePadding = dimensionResource(id = R.dimen._24dp),
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .tabIndicatorOffset(tabPositions[tabIndex])
                    .height(dimensionResource(id = R.dimen._2dp))
                    .background(colorResource(id = R.color.purple)),
            )
        }
    ) {
        tabNames.forEachIndexed { index, tabName ->
            Tab(
                selected = index == tabIndex,
                onClick = { onTabClick(index) },
                text = {
                    if (index == selectedTabIndex) {
                        Text(
                            text = tabName,
                            fontSize = dimensionResource(id = R.dimen._15sp).value.sp,
                            fontFamily = FontFamily(Font(R.font.inter_semi_bold)),
                            color = colorResource(id = R.color.black_dark)
                        )
                    } else {
                        Text(
                            text = tabName,
                            fontSize = dimensionResource(id = R.dimen._15sp).value.sp,
                            fontFamily = FontFamily(Font(R.font.inter_medium)),
                            color = colorResource(id = R.color.gray_darker)
                        )
                    }
                }
            )
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = dimensionResource(id = R.dimen._0_33dp)),
        color = colorResource(id = R.color.gray_dark)
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._16dp)))
    content()
}

@Composable
@Preview
fun TabsPreview() =
    Tabs(
        tabIndex = 0,
        selectedTabIndex = 0,
        tabNames = listOf(
            stringResource(id = R.string.all),
            stringResource(id = R.string.designers),
            stringResource(id = R.string.analysts),
            stringResource(id = R.string.managers),
            stringResource(id = R.string.ios)
        ),
        onTabClick = {},
        content = {}
    )


