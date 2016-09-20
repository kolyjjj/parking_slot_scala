package li.koly

import org.scalatest.FunSpec

class ParkingSlotManagerTest extends FunSpec{
  describe("Parking Slot Manager") {
    it("should return a large slot given a large vehicle") {
      assert(ParkingSlotManager.park(Vehicle(LARGE_VEHICLE)) == Ticket(Slot(1, LARGE_SPACE)))
    }

    it("should return a middle slot given a middle vehicle") {
      assert(ParkingSlotManager.park(Vehicle(MIDDLE_VEHICLE)) == Ticket(Slot(3, MIDDLE_SPACE)))
    }

    it("should return a large slot given a middle vehicle when there is no available middle slot") {
      ParkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      ParkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      ParkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      assert(ParkingSlotManager.park(Vehicle(MIDDLE_VEHICLE)) == Ticket(Slot(1, LARGE_SPACE)))
    }
  }
}
