package com.example.pharmamobile.domain.model

data class Pedido(
    val id:Long,
    val cliente: String,
    val detalles: List<DetallePedido>,
    val estado: EstadoPedido
)
