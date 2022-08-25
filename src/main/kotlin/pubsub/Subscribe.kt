package pubsub

import redis.clients.jedis.JedisPooled
import redis.clients.jedis.JedisPubSub
import java.util.concurrent.Executors


object Subscribe {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)
    val executor = Executors.newFixedThreadPool(2)

    // slide begin
    class MyListener : JedisPubSub() {
      override fun onMessage(channel: String, msg: String) {
        println("Received a message for channel $channel: $msg")
      }

      override fun onSubscribe(channel: String, count: Int) {}
      override fun onUnsubscribe(channel: String, count: Int) {}
      override fun onPSubscribe(pattern: String, count: Int) {}
      override fun onPUnsubscribe(pattern: String, count: Int) {}
      override fun onPMessage(pattern: String, channel: String, msg: String) {}
    }

    executor.submit {
      jedis.subscribe(MyListener(), "stocks:price:AAPL")
    }

    executor.submit {
      jedis.subscribe(MyListener(), "stocks:volume:GOOG")
    }

    Thread.sleep(100000)
    // slide end
  }
}