package com.razaghimahdi.fullnavigationdrawer

data class Cloth(
    val name: String,
    val image: Int,
    val seller: String,
    val desc: String,
    val price: String
)

val cloths = listOf(
    Cloth(
        "New Shoes",
        R.drawable.shoes01,
        "Solo",
        "Just sample shoes to show how cool is that!",
        "£48.50"
    ),
    Cloth(
        "New Shoes 2",
        R.drawable.shoes02,
        "Solo",
        "Just sample shoes to show how cool is that!",
        "£50.99"
    ),
    Cloth(
        "New Shoes",
        R.drawable.shoes01,
        "Solo",
        "Just sample shoes to show how cool is that!",
        "£48.50"
    ),
    Cloth(
        "New Shoes 2",
        R.drawable.shoes02,
        "Solo",
        "Just sample shoes to show how cool is that!",
        "£50.99"
    ),
)