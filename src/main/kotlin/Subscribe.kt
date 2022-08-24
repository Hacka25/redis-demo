import Stocks.AAPL
import Stocks.GOOG
import redis.clients.jedis.JedisPooled
import redis.clients.jedis.JedisPubSub
import java.util.concurrent.Executors


object Subscribe {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)
    val executor = Executors.newFixedThreadPool(2)

    // Subscribe begin
    class MyListener : JedisPubSub() {
      override fun onMessage(channel: String, message: String) {
        println("Received a message for channel $channel: $message")
      }

      override fun onSubscribe(channel: String, count: Int) {}
      override fun onUnsubscribe(channel: String, count: Int) {}
      override fun onPSubscribe(pattern: String, count: Int) {}
      override fun onPUnsubscribe(pattern: String, count: Int) {}
      override fun onPMessage(pattern: String, channel: String, message: String) {}
    }

    executor.submit {
      jedis.subscribe(MyListener(), "stocks:price:${AAPL.name}")
    }

    executor.submit {
      jedis.subscribe(MyListener(), "stocks:volume:${GOOG.name}")
    }

    Thread.sleep(100000)
    // Subscribe end
  }
}