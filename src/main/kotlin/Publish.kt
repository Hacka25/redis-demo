import redis.clients.jedis.JedisPooled
import kotlin.random.Random

object Publish {

  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)

    // Publish begin
    repeat(100) {
      val stock = Stocks.values()[Random.nextInt(Stocks.values().size)].name
      val channel = "stocks:$stock"
      val temp = Random.nextInt(32, 100)

      println("Publishing $channel price $temp")

      jedis.publish(channel, temp.toString())

      Thread.sleep(1000)
    }
    // Publish end
  }
}