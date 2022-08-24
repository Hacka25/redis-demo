import redis.clients.jedis.JedisPooled

object HSet2 {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)

    // HSet2 begin
    data class User(val map: Map<String, String>) {
      val username: String by map
      val firstName: String by map
      val lastName: String by map
    }

    val map1 = mapOf(
      "username" to "martina",
      "firstName" to "Martina",
      "lastName" to "Elisa",
    )
    val user1 = User(map1)
    jedis.hset("user:123", user1.map)

    val map2 = jedis.hgetAll("user:123")
    val user2 = User(map2)
    println("user2=$user2")
    // HSet2 end
  }
}