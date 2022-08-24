import redis.clients.jedis.JedisPooled

object Set1 {
  @JvmStatic
  fun main(args: Array<String>) {
    // Set1 begin
    val jedis = JedisPooled("localhost", 6379)

    jedis.set("key1", "Hello");

    val v1 = jedis.get("key1")
    println("key1=$v1")

    jedis.del("key1")

    val v2 = jedis.get("key1")
    println("key1=$v2")
    // Set1 end
  }
}