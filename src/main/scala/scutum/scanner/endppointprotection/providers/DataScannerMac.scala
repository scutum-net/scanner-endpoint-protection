package scutum.scanner.endppointprotection.providers

import java.io.{BufferedReader, InputStreamReader}
import java.nio.file.Path

import scutum.scanner.endppointprotection.contracts.DataScanner
import scutum.scanner.endppointprotection.contracts.DataScanner._
import java.nio.file.StandardWatchEventKinds._


class DataScannerMac extends DataScanner {
  override def scan(): MachineData = ???


  def getProcesses: List[ProcessActivity] = {
    val input = new BufferedReader(new InputStreamReader(
      Runtime.getRuntime.exec("ps -ej").getInputStream))

    Stream
      .continually(input.readLine)
      .takeWhile(isNotEndOfStream)
      .filter(isNotHeader)
      .map(parseLine)
      .map(i => ProcessActivity(i, List(), List()))
      .toList
  }


  private def isNotHeader(x: String) = !x.startsWith("USER")

  private def isNotEndOfStream(x: String) = x != null

  private def parseLine(x: String): Process = {
    val params = x.split(" ", -1).filter(_ != "").toList
    Process(params(1).toInt, params(2).toInt, params(9), params.head, "")
  }
}
