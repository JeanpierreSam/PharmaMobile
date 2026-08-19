package com.example.pharmamobile.demo

import com.example.pharmamobile.domain.result.ResultadoProductos

fun mostrarResultado(resultado: ResultadoProductos){
    when(resultado){
        ResultadoProductos.cargando -> {
            println(
                "Cargando productos"
            )
        }
        is ResultadoProductos.Exito -> {
            println(
                "Productos encontrados: ${resultado.productos.size}"
            )
        }
        is ResultadoProductos.Error -> {
            println(
                "Error: ${resultado.mensaje}"
            )
        }
    }
}