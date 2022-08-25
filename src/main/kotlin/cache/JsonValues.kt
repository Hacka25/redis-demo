package cache

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redis.clients.jedis.JedisPooled

object JsonValues {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)

    // slide begin
    @Serializable
    data class User(
      val username: String,
      val firstName: String,
      val lastName: String
    )

    val user1 = User("martina", "Martina", "Elisa")
    val json1 = Json.encodeToString(user1)
    jedis.set("user:123", json1)

    val json2 = jedis.get("user:123")
    val user2 = Json.decodeFromString<User>(json2)
    println("user2=$user2")
    // slide end
  }
}