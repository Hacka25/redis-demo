import redis.clients.jedis.JedisPooled
import redis.clients.jedis.JedisPubSub


object Subscribe {
  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)

    // Subscribe begin
    class MyListener : JedisPubSub() {
      override fun onMessage(channel: String, message: String) {
        println("Received a message for channel: $channel: $message")
      }

      override fun onSubscribe(channel: String, subscribedChannels: Int) {}
      override fun onUnsubscribe(channel: String, subscribedChannels: Int) {}
      override fun onPSubscribe(pattern: String, subscribedChannels: Int) {}
      override fun onPUnsubscribe(pattern: String, subscribedChannels: Int) {}
      override fun onPMessage(pattern: String, channel: String, message: String) {}
    }

    val myListener = MyListener()

    jedis.subscribe(myListener, "weather:${Cities.Concord.name}")

    Thread.sleep(100000)
    // Subscribe end
  }
}