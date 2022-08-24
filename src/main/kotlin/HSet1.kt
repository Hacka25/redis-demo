import redis.clients.jedis.JedisPooled

object HSet1 {
  @JvmStatic
  fun main(args: Array<String>) {
    // HSet1 begin
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
    // HSet1 end
  }
}