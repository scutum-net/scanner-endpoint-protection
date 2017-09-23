package scutum.scanner.endpointprotection

import java.io.File
import java.net.InetAddress

import com.google.inject._
import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.ScalaModule
import scutum.core.contracts.IScanner
import scutum.scanner.endpointprotection.providers.DataScannerWindows

class Injector extends AbstractModule with ScalaModule with LazyLogging {

  override def configure(): Unit = {
    val logFile = new File("./logback.xml")
    if (logFile.exists) System.setProperty("logback.configurationFile", logFile.getCanonicalPath)
    logger.info(s"logback loaded: ${logFile.getCanonicalPath} ${logFile.exists}")
  }

  @Provides
  @Singleton def getConfig: Config = {
    val logFile = new File("./app.conf")
    logger.info(s"config loaded: ${logFile.getCanonicalPath} ${logFile.exists}")
    if (logFile.exists) ConfigFactory.parseFile(logFile) else ConfigFactory.load("app.conf")
  }

  @Provides
  @Singleton def getScanners(@Inject config: Config): IScanner = {
    val hostName = InetAddress.getLocalHost().getHostName()
    val osName = System.getProperty("os.name").toLowerCase
    new DataScannerWindows(hostName, config.getString("conf.customerId"), 1, 1)
  }
}
