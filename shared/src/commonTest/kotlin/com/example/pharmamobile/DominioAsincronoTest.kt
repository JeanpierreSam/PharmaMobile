package com.example.pharmamobile

import com.example.pharmamobile.data.ProductoRepositorySimulado
import com.example.pharmamobile.demo.mostrarResultado
import com.example.pharmamobile.domain.query.buscarPorId
import com.example.pharmamobile.domain.query.nombres
import com.example.pharmamobile.domain.result.ResultadoProductos
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DominioAsincronoTest {

    private val repositorio = ProductoRepositorySimulado()

    @Test
    fun obtenerProductosDevuelveElCatalogo() = runTest {
        val productos = repositorio.obtenerProductos()
        println("Productos obtenidos: ${productos.size} -> ${productos.nombres()}")
        assertEquals(expected = 3, actual = productos.size)
    }

    @Test
    fun observarEstadosEmiteDosValores() = runTest {
        val estados = mutableListOf<String>()
        repositorio.observarEstados().collect { estado ->
            println("Estado emitido: $estado")
            estados += estado
        }
        assertEquals(expected = listOf("Iniciando", "Finalizado"), actual = estados)
    }

    @Test
    fun observarProductosEmiteTresListas() = runTest {
        val emisiones = repositorio.observarProductos().toList()
        emisiones.forEachIndexed { i, lista ->
            println("Emisión $i -> ${lista.size} productos, stock id 1: ${lista.buscarPorId(1L)?.stock}")
        }
        assertEquals(expected = 3, actual = emisiones.size)
        assertEquals(expected = 90, actual = emisiones.last().buscarPorId(1L)?.stock)
    }

    @Test
    fun cargarProductosEmiteCargandoYExito() = runTest {
        val recibidos = repositorio.cargarProductos().toList()
        recibidos.forEach { mostrarResultado(it) }
        assertTrue(recibidos.first() is ResultadoProductos.Cargando)
        assertTrue(recibidos.last() is ResultadoProductos.Exito)
    }
}