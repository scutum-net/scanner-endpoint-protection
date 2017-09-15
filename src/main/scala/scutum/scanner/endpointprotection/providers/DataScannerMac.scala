package scutum.scanner.endpointprotection.providers

import java.io._
import java.time.LocalDateTime

import scutum.core.contracts.endpointprotection.{IDataScanner, MachineData, ProcessData}

import scala.collection.JavaConverters._

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


  def getProcesses: List[ProcessData] = {
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

  private def parseLine(x: String): ProcessData = {
    val params = x.split(" ", -1).filter(_ != "").toList
    new ProcessData(params(1).toInt, params(2).toInt, params(9), params.head, "", 0)
  }
}
