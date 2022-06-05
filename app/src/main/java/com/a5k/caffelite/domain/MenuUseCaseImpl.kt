package com.a5k.caffelite.domain

import com.a5k.caffelite.domain.entity.MenuCaffe
import com.a5k.caffelite.domain.repository.CaffeRepository
import javax.inject.Inject

class MenuUseCaseImpl @Inject constructor(
    private val repository: CaffeRepository
) : MenuUseCase {

    override suspend fun getMenu(idCaffe: Int): List<MenuCaffe> =
        repository.getMenu(idCaffe)
}