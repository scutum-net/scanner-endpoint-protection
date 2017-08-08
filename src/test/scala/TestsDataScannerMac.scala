import org.scalatest.WordSpecLike
import scutum.scanner.endpointprotection.providers.DataScannerMac


class TestsDataScannerMac extends  WordSpecLike {
  "Mac scanner " must {
    "Collect data" in {
      val scanner = new DataScannerMac("machine", "customer", 1, 1)
      val processes = scanner.getProcesses
      assert(processes.nonEmpty)

      val machineData = scanner.scan()
      assert(machineData.getCustomerId == "customer")


    }
  }
}
