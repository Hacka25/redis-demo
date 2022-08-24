import redis.clients.jedis.JedisPooled
import kotlin.random.Random

object Publish {

  @JvmStatic
  fun main(args: Array<String>) {
    val jedis = JedisPooled("localhost", 6379)

    // Publish begin
    repeat(100) {
      val city = Cities.values()[Random.nextInt(3)].name
      val channel = "weather:$city"
      val temp = Random.nextInt(32, 100)

      println("Publishing $channel temperature $temp")

      jedis.publish(channel, temp.toString())

      Thread.sleep(1000)
    }
    // Publish end
  }
}