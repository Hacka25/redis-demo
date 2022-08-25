package workers

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import redis.clients.jedis.JedisPooled

// slide begin
class Worker(val id: Int) {
  val jedis = JedisPooled("localhost", 6379)
  var workCount = 0

  fun readQueue(queueName: String): WorkDesc {
    val json = jedis.brpop(0.0, queueName).value
    return Json.decodeFromString(json)
  }

  fun executeWork(workDesc: WorkDesc) {
    println("Worker $id starting job: $workDesc (Job count: $workCount)")
    Thread.sleep(workDesc.timeRequired * 1000L)
    workCount++
    println("Worker $id completed job: $workDesc (Job count: $workCount)")
  }
}
// slide end
