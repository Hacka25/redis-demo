package pubsub

import redis.clients.jedis.JedisPooled
import kotlin.random.Random

object Publish {

  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)
    val size = Stocks.values().size

    // slide begin
    repeat(200) {
      val stock = Stocks.values()[Random.nextInt(size)].name
      val priceChannel = "stocks:price:$stock"
      val volumeChannel = "stocks:volume:$stock"
      val price = Random.nextInt(32, 100)
      val volume = Random.nextInt(1000, 2000)

      println("Publishing $priceChannel price $price")
      jedis.publish(priceChannel, price.toString())

      println("Publishing $volumeChannel volume $volume")
      jedis.publish(volumeChannel, volume.toString())

      Thread.sleep(500)
    }
    // slide end
  }
}