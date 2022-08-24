import redis.clients.jedis.JedisPooled

object Set1 {
  @JvmStatic
  fun main(args: Array<String>) {
    // Set1 begin
    val jedis = JedisPooled("localhost", 6379)

    repeat(5) { jedis.set("foo:$it", "Hello-$it"); }

    repeat(5) {
      val v = jedis.get("foo:$it")
      println("foo:$it=$v")
    }

    repeat(5) { jedis.del("foo:$it"); }

    repeat(5) {
      val v = jedis.get("foo:$it")
      println("foo:$it=$v")
    }
    // Set1 end
  }
}