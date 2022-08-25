package workers

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redis.clients.jedis.JedisPooled
import kotlin.random.Random

object SubmitWork {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)
    var cnt = 0

    // slide begin
    repeat(10000) {
      val workDesc = WorkDesc(cnt++, Random.nextInt(1, 15))
      println("Submitting work item: $workDesc")
      val json = Json.encodeToString(workDesc)
      jedis.lpush("work_queue:render", json)
      Thread.sleep(1000)
    }
    // slide end
  }
}