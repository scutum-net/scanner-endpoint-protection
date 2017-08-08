package scutum.scanner.endpointprotection

import java.io.File
import com.google.inject._
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.ScalaModule

class Injector extends AbstractModule with ScalaModule with LazyLogging {

  override def configure(): Unit = {
    val logFile = new File("./app.conf")
    logger.info(s"config loaded: ${logFile.getCanonicalPath} ${logFile.exists}")
    if (logFile.exists) ConfigFactory.parseFile(logFile) else ConfigFactory.load("app.conf")
  }

  @Provides
  @Singleton def getConfig: Config = {
    val logFile = new File("./app.conf")
    logger.info(s"config loaded: ${logFile.getCanonicalPath} ${logFile.exists}")
    if (logFile.exists) ConfigFactory.parseFile(logFile) else ConfigFactory.load("app.conf")
  }

}
