package scutum.scanner.endppointprotection.contracts


import java.time.LocalDateTime
import scutum.scanner.endppointprotection.contracts.DataScanner.MachineData


trait DataScanner {
  def scan(): MachineData
}

object DataScanner {

  // all possible operation on fd [POSIX]
  object FileOperations extends Enumeration {
    val read, write, execute = Value
  }

  // example { 234 1 'C:\program files\chrome.exe' 'admin' 'e7b4fd77491ef2ba13d47ab7df6eb4c4' }
  case class Process(id: Int, parentId: Int, path: String, owner: String, hash: String)

  // example { 'C:\Documents\report.txt', read }
  case class FilesActivity(file: String, operations: FileOperations.Value)

  // example { TCP 8081 read}
  case class NetworkActivity(interface: String, port: Int, operation: FileOperations.Value)

  // process and its network activity
  case class ProcessActivity(process: Process, network: List[NetworkActivity], files: List[FilesActivity])

  // machine data
  case class MachineData(customerId: String, timeStamp: LocalDateTime, machineId: String, processes: List[ProcessActivity])

}
