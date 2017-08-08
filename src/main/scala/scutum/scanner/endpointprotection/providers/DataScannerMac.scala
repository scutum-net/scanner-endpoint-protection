package scutum.scanner.endpointprotection.providers

import java.io._
import java.time.LocalDateTime
import scala.collection.JavaConverters._
import scutum.scanner.endpointprotection.contracts._

class DataScannerMac(machineId: String, customerId: String, version: Int, scanType: Int) extends IDataScanner {

  override def scan(): MachineData = {
    new MachineData(
      machineId,
      customerId,
      version,
      scanType,
      LocalDateTime.now(),
      getProcesses.asJava)
  }


  def getProcesses: List[Process] = {
    val input = new BufferedReader(new InputStreamReader(
      Runtime.getRuntime.exec("ps -ej").getInputStream))

    Stream
      .continually(input.readLine)
      .takeWhile(isNotEndOfStream)
      .filter(isNotHeader)
      .map(parseLine)
      .toList
  }


  private def isNotHeader(x: String) = !x.startsWith("USER")

  private def isNotEndOfStream(x: String) = x != null

  private def parseLine(x: String): Process = {
    val params = x.split(" ", -1).filter(_ != "").toList
    new Process(params(1).toInt, params(2).toInt, params(9), params.head, "", 0)
  }
}
