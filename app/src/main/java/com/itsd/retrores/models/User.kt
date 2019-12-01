package com.itsd.retrores.models


// Data Class For Taking Response Data
data class User (
    val id: Int? = 0,
    val fb_id: String? = null,
    val name: String? = null,
    val mobile: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val birthday: String? = null,
    val status: Boolean = false,
    val created_at: String? = null
 )