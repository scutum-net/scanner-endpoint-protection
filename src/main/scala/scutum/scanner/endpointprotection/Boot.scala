package scutum.scanner.endpointprotection

import com.google.gson.Gson
import com.google.inject.Guice
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.InjectorExtensions._
import scutum.scanner.endpointprotection.contracts.IDataScanner

object Boot extends LazyLogging {
  def main(args: Array[String]): Unit = {
    val serializer = new Gson
    logger.info("starting application")

    val injector = Guice.createInjector(new Injector())
    logger.info("di created")

    val config = injector.instance[Config]
    logger.info("config loaded")

    val scanner = injector.instance[IDataScanner]

    while(true){
      Thread.sleep(config.getLong("conf.interval"))
      val data =  serializer.toJson(scanner.scan())
      logger.info(s"something happened $data")
    }
  }
}
