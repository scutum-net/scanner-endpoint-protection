package scutum.scanner.endppointprotection.contracts

import scutum.scanner.endppointprotection.contracts.DataScanner.MachineData


trait DataScanner {
  def scan(): MachineData
}

object DataScanner {
  case class MachineData(machineId: String)
}
