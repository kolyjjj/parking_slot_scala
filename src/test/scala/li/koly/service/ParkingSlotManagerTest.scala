package li.koly.service

import li.koly.domain._
import org.scalatest.FunSpec

class ParkingSlotManagerTest extends FunSpec {

  private def newParkingSlotManager(): ParkingSlotManager = {
    val largeSlots: List[Slot] = List(Slot(1, SlotSize(1, LARGE_SPACE)), Slot(2, SlotSize(1, LARGE_SPACE)))
    val middleSlots: List[Slot] = List(Slot(3, SlotSize(1, MIDDLE_SPACE)), Slot(4, SlotSize(1, MIDDLE_SPACE)), Slot(5, SlotSize(1, MIDDLE_SPACE)))
    val smallSlots: List[Slot] = List(Slot(6, SlotSize(1, SMALL_SPACE)), Slot(7, SlotSize(1, SMALL_SPACE)), Slot(8, SlotSize(1, SMALL_SPACE)))
    new ParkingSlotManager(largeSlots, middleSlots, smallSlots)
  }

  describe("Parking Slot Manager") {
    it("should return a large slot given a large vehicle") {
      val parkingSlotManager: ParkingSlotManager = newParkingSlotManager()
      assert(parkingSlotManager.park(Vehicle(LARGE_VEHICLE)).get == Ticket(Slot(1, SlotSize(1, LARGE_SPACE), SlotSize(0, LARGE_SPACE))))
    }

    it("should return a middle slot given a middle vehicle") {
      val parkingSlotManager: ParkingSlotManager = newParkingSlotManager()
      assert(parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE)).get == Ticket(Slot(3, SlotSize(1, MIDDLE_SPACE), SlotSize(0, MIDDLE_SPACE))))
    }

    it("should return a large slot given a middle vehicle when there is no available middle slot") {
      val parkingSlotManager: ParkingSlotManager = newParkingSlotManager()
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      assert(parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE)).get == Ticket(Slot(1, SlotSize(1, LARGE_SPACE), SlotSize(0, MIDDLE_SPACE))))
    }

    it("should return a large slot with 1 available small space given a small vehicle when all middle slots are parked") {
      val parkingSlotManager: ParkingSlotManager = newParkingSlotManager()
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      assert(parkingSlotManager.park(Vehicle(SMALL_VEHICLE)).get == Ticket(Slot(1, SlotSize(1, LARGE_SPACE), SlotSize(1, SMALL_SPACE))))
    }

    it("should return a large slot with 1 available small space given a small vehicle when all middle slots are parked and one large slot is parked") {
      val parkingSlotManager: ParkingSlotManager = newParkingSlotManager()
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(LARGE_VEHICLE))
      assert(parkingSlotManager.park(Vehicle(SMALL_VEHICLE)).get == Ticket(Slot(2, SlotSize(1, LARGE_SPACE), SlotSize(1, SMALL_SPACE))))
    }

    it("should return a large slot with 0 available small space given a small vehicle when all middle slots are parked") {
      val parkingSlotManager: ParkingSlotManager = newParkingSlotManager()
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      assert(parkingSlotManager.park(Vehicle(SMALL_VEHICLE)).get == Ticket(Slot(1, SlotSize(1, LARGE_SPACE), SlotSize(0, SMALL_SPACE))))
    }

    it("should return no slot when there is no middle slot and all large is parked by small vehicle") {
      val parkingSlotManager: ParkingSlotManager = newParkingSlotManager()
      parkingSlotManager.park(Vehicle(LARGE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      parkingSlotManager.park(Vehicle(SMALL_VEHICLE))
      assert(parkingSlotManager.park(Vehicle(MIDDLE_VEHICLE)).isEmpty)
    }

    it("should park tiny vehicle to small slot") {
      val parkingSlotManager: ParkingSlotManager = newParkingSlotManager()
      assert(parkingSlotManager.park(Vehicle(TINY_VEHICLE)).get == Ticket(Slot(6, SlotSize(1, SMALL_SPACE), SlotSize(0, SMALL_SPACE))))
    }

  }
}
