package workers

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import redis.clients.jedis.JedisPooled
import java.util.concurrent.Executors

object Workers {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)

    // slide begin
    class Worker(val id: Int, val jedis: JedisPooled) {
      var workCount = 0
      fun readQueue(): WorkDesc {
        val json = jedis.brpop(0.0, "work_queue:render").value
        val workDesc = Json.decodeFromString<WorkDesc>(json)
        return workDesc
      }

      fun executeWork(workDesc: WorkDesc) {
        workCount++
        println("Worker $id executing job: $workDesc (Job count: $workCount)")
        Thread.sleep(workDesc.timeRequired * 1000L)
      }
    }

    val executor = Executors.newCachedThreadPool()
    val workers = List(5) { Worker(it, jedis) }
    workers.forEach { worker ->
      executor.submit {
        while (true) {
          val workDesc = worker.readQueue()
          worker.executeWork(workDesc)
        }
      }
    }
    // slide end
  }


  /*

   */
}