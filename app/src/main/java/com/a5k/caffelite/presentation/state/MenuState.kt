package com.a5k.caffelite.presentation.state

import com.a5k.caffelite.domain.entity.MenuCaffe

sealed interface MenuState {
    data class Success(val listMenu: List<MenuCaffe>): MenuState
    data class Error(val error: Throwable): MenuState
}