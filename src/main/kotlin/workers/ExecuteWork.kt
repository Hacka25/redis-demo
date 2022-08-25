package workers

import java.util.concurrent.Executors

object ExecuteWork {
  @JvmStatic
  fun main(args: Array<String>) {
    // slide begin
    val workerCount = 5
    val executor = Executors.newCachedThreadPool()
    val workers = List(workerCount) { Worker(it) }
    workers.forEach { worker ->
      executor.submit {
        while (true) {
          val workDesc = worker.readQueue("work_queue:render")
          worker.executeWork(workDesc)
        }
      }
    }
    // slide end
  }
}