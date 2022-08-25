package workers

import redis.clients.jedis.JedisPooled

object ClearQueue {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)
    val cnt = jedis.lpop("work_queue:render", 100000).size
    println("Removed $cnt items")
  }
}