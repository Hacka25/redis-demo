package strings

import redis.clients.jedis.JedisPooled

object HSet {
  @JvmStatic
  fun main(args: Array<String>) {
    // slide begin
    val jedis = JedisPooled("localhost", 6379)

    jedis.hset(
      "user:123",
      mapOf(
        "username" to "martina",
        "firstName" to "Martina",
        "lastName" to "Elisa",
      )
    )

    val valMap: Map<String, String> = jedis.hgetAll("user:123")
    println("user:123=$valMap")
    // slide end
  }
}