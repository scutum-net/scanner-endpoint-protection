package scutum.scanner.endpointprotection

import com.google.inject._
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

object Boot extends LazyLogging {
  def main(args: Array[String]): Unit = {
    logger.info("starting application")

    val injector = Guice.createInjector(new Injector())
    logger.info("di created")

    //val config = injector.getInstance[Config]
    logger.info("config loaded")

    while(true){
      Thread.sleep(1000)
      logger.info("something happened")
    }
  }
}
