package helloworld

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    val id: Int = -1,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val age: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("birth_date")
    val birthDate: Date = Date()
)