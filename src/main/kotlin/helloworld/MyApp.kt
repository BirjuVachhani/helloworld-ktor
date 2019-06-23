package helloworld

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import java.text.DateFormat
import java.util.*

fun Application.main() {
    install(DefaultHeaders)
    install(StatusPages) {
        exception<Throwable> { e ->
            call.respond(Error(e.localizedMessage))
        }
    }
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setLenient()
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
    routing {
        // e.g http://localhost:8082
        get("/") {
            call.respondText(
                """<div align=center style="color:#ff4433; font-family=roboto; font-size:2vw;">Welcome to ktor demo app</div>""",
                ContentType.Text.Html
            )
        }
        // e.g http://localhost:8082/users
        get("/users") {
            call.respond(users)
        }
        // e.g http://localhost:8082/user/1
        get("/user/{id}") {
            val id: Int =
                call.parameters["id"]?.toIntOrNull()
                    ?: throw IllegalArgumentException("parameter id is not present")
            call.respond(users.firstOrNull { it.id == id } ?: throw Exception("No user found"))
        }
        // e.g http://localhost:8082/user?id=2
        get("/user") {
            val id: Int =
                call.parameters["id"]?.toIntOrNull()
                    ?: throw IllegalArgumentException("parameter id is not present")
            call.respond(users.firstOrNull { it.id == id } ?: throw Exception("No user found"))
        }
    }
}

val users = arrayListOf(
    User(
        1,
        firstName = "Birju",
        lastName = "Vachhani",
        age = 22,
        email = "brvachhani@gmail.com",
        birthDate = Date()
    ),
    User(
        2,
        firstName = "Rajan",
        lastName = "Sharma",
        age = 22,
        email = "rajansharma9697@gmail.com",
        birthDate = Date()
    ),
    User(
        3,
        firstName = "Mayur",
        lastName = "Chhapra",
        age = 22,
        email = "mayurchhapra@gmail.com",
        birthDate = Date()
    ),
    User(
        4,
        firstName = "Varis",
        lastName = "Bhalala",
        age = 22,
        email = "varispatel@gmail.com",
        birthDate = Date()
    ),
    User(
        5,
        firstName = "Sunny",
        lastName = "Radadiya",
        age = 22,
        email = "radadiyasunny@gmail.com",
        birthDate = Date()
    ),
    User(
        6,
        firstName = "Rajat",
        lastName = "Beladiya",
        age = 22,
        email = "rajatbeladiya@gmail.com",
        birthDate = Date()
    ),
    User(
        7,
        firstName = "Hardik",
        lastName = "Bharatiya",
        age = 22,
        email = "bharatiahardik@gmail.com",
        birthDate = Date()
    )
)

