package com.example.planetapp.models
import com.example.planetapp.R
data class Planet(
    val id: Int,
    val name: String,
    val type: String,
    val galaxy: String,
    val distanceFromSun: String,
    val diameter: String,
    val characteristics: String,
    val imageRes: Int,
    var isFavorite: Boolean = false
)

val planetList = listOf(
    Planet(
        id = 1,
        name = "Mercury",
        type = "Terrestrial",
        galaxy = "Milky Way",
        distanceFromSun = "57.9 million km",
        diameter = "4,879 km",
        characteristics = "Smallest planet, no atmosphere, very hot and cold.",
        imageRes = R.drawable.mercurio
    ),
    Planet(
        id = 2,
        name = "Venus",
        type = "Terrestrial",
        galaxy = "Milky Way",
        distanceFromSun = "108.2 million km",
        diameter = "12,104 km",
        characteristics = "Hottest planet, thick toxic atmosphere.",
        imageRes = R.drawable.venus
    ),
    Planet(
        id = 3,
        name = "Earth",
        type = "Terrestrial",
        galaxy = "Milky Way",
        distanceFromSun = "149.6 million km",
        diameter = "12,742 km",
        characteristics = "Supports life, has water and atmosphere.",
        imageRes = R.drawable.terra
    ),
    Planet(
        id = 4,
        name = "Mars",
        type = "Terrestrial",
        galaxy = "Milky Way",
        distanceFromSun = "227.9 million km",
        diameter = "6,779 km",
        characteristics = "Known as the Red Planet, may have had water.",
        imageRes = R.drawable.marte
    ),
    Planet(
        id = 5,
        name = "Jupiter",
        type = "Gas Giant",
        galaxy = "Milky Way",
        distanceFromSun = "778.5 million km",
        diameter = "139,820 km",
        characteristics = "Largest planet, has a Great Red Spot storm.",
        imageRes = R.drawable.jupiter
    ),
    Planet(
        id = 6,
        name = "Saturn",
        type = "Gas Giant",
        galaxy = "Milky Way",
        distanceFromSun = "1.4 billion km",
        diameter = "116,460 km",
        characteristics = "Famous for its ring system.",
        imageRes = R.drawable.saturno
    ),
    Planet(
        id = 7,
        name = "Uranus",
        type = "Ice Giant",
        galaxy = "Milky Way",
        distanceFromSun = "2.9 billion km",
        diameter = "50,724 km",
        characteristics = "Rotates on its side, has faint rings.",
        imageRes = R.drawable.urano
    ),
    Planet(
        id = 8,
        name = "Neptune",
        type = "Ice Giant",
        galaxy = "Milky Way",
        distanceFromSun = "4.5 billion km",
        diameter = "49,244 km",
        characteristics = "Farthest known planet, strong winds and storms.",
        imageRes = R.drawable.netuno
    )
)
