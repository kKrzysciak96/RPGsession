package com.eltescode.rpgsession.features.user.presentation.profile.user.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp


@Composable
fun LazyVerticalGridWithHeader(list: List<String>, userPicture: @Composable () -> Unit) {
    LazyVerticalGrid(
        columns = object : GridCells {
            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int,
                spacing: Int
            ): List<Int> {
                val firstColumn = (availableSize - spacing) * 2 / 3
                val secondColumn = availableSize - spacing - firstColumn
                return listOf(firstColumn, secondColumn)
            }
        },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        list.forEachIndexed { index, text ->
            if (index == 0) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    userPicture()
                }
            } else {
                item(span = { GridItemSpan(1) }) {
                    BaseCard(text = text)
                }
            }
        }
    }
}