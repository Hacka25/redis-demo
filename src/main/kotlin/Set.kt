import redis.clients.jedis.JedisPooled

fun main() {
  val jedis = JedisPooled("localhost", 6379)

  repeat(5) { jedis.set("foo:$it", "Hello-$it"); }

  repeat(5) { println("foo:$it=${jedis.get("foo:$it")}") }

  repeat(5) { jedis.del("foo:$it"); }

  repeat(5) { println("foo:$it=${jedis.get("foo:$it")}") }
}