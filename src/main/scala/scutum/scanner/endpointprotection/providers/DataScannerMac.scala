package scutum.scanner.endpointprotection.providers

import java.io._
import scutum.core.contracts.{IScanner, ScannedData}

/*
class DataScannerMac(machineId: String, customerId: String, version: Int, scanType: Int) extends IScanner {

  override def scan(): ScannedData = ???

  def getProcesses: List[Any] = {
    val input = new BufferedReader(new In*putStreamReader(
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

  /*
  private def parseLine(x: String): ProcessData = {
    val params = x.split(" ", -1).filter(_ != "").toList
    new ProcessData(params(1).toInt, params(2).toInt, params(9), params.head, "", 0)
  }
  */
}
*/