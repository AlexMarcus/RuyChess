package com.example.ruychess.ui

import androidx.compose.runtime.Immutable

@Immutable
interface UiEvent<T> {
    fun onClick(clickedItem: T)
}