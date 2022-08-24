import redis.clients.jedis.JedisPooled

object HSet2 {
  @JvmStatic
  fun main(args: Array<String>) {
    // HSet2 begin
    val jedis = JedisPooled("localhost", 6379)

    data class User(val map: Map<String, String>) {
      val username: String by map
      val firstName: String by map
      val lastName: String by map
    }

    val user1 = User(
      mapOf(
        "username" to "martina",
        "firstName" to "Martina",
        "lastName" to "Elisa",
      )
    )
    jedis.hset("user:123", user1.map)

    val user2 = User(jedis.hgetAll("user:123"))
    println("user2=$user2")
    // HSet2 end
  }
}