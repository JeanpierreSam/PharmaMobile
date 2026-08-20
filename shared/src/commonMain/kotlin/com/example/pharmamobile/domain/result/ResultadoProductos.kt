package com.example.pharmamobile.domain.result

import com.example.pharmamobile.domain.model.Producto

sealed class ResultadoProductos {
    data object Cargando : ResultadoProductos()
    data class Exito(
        val productos: List<Producto>
    ): ResultadoProductos()
    data class Error(
        val mensaje: String
    ): ResultadoProductos()

}