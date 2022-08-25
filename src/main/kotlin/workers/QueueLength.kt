package workers

import redis.clients.jedis.JedisPooled

object QueueLength {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)
    while (true) {
      val cnt = jedis.llen("work_queue:render")
      println("$cnt items in work_queue:render queue")
      Thread.sleep(1000)
    }
  }
}