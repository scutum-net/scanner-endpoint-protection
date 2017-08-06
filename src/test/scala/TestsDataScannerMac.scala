import org.scalatest.WordSpecLike
import scutum.scanner.endppointprotection.providers.DataScannerMac


class TestsDataScannerMac extends  WordSpecLike {
  "Mac scanner " must {
    "Collect data" in {
      val scanner = new DataScannerMac()
      val processes = scanner.getProcesses

      assert(processes.nonEmpty)
    }
  }
}
