package workers

import java.util.concurrent.Executors

object ExecuteWork {
  @JvmStatic
  fun main(args: Array<String>) {
    // slide begin
    val executor = Executors.newCachedThreadPool()
    // Initialize Workers
    val workers = List(5) { Worker(it) }
    workers.forEach { worker ->
      executor.submit {
        while (true) {
          val workDesc = worker.removeWorkFromQueue("work_queue:render")
          worker.executeWork(workDesc)
        }
      }
    }
    // slide end
  }
}