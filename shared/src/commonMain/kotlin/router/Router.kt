package router

import kotlinx.serialization.Serializable

/**
 * @author yuming
 */

@Serializable
data object HomeRoute

@Serializable
data class DetailRoute(
    val item: CustomType
)

@Serializable
data class CustomType(
    val id: String,
    val name: String,
    val description: String
)