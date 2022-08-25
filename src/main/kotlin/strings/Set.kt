package strings

import redis.clients.jedis.JedisPooled

object Set {
  @JvmStatic
  fun main(args: Array<String>) {
    // slide begin
    val jedis = JedisPooled("localhost", 6379)

    jedis.set("key1", "Hello");

    val v1 = jedis.get("key1")
    println("key1=$v1")

    jedis.del("key1")

    val v2 = jedis.get("key1")
    println("key1=$v2")
    // slide end
  }
}