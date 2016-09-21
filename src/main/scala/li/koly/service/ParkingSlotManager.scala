package li.koly.service

import li.koly.domain._
import li.koly.exception.UnknownVehicleType

private class ParkingSlotManager(val largeSlots: List[Slot], middleSlots: List[Slot], smallSlots: List[Slot]) {

  val vehicleSlotsMapping: Map[VehicleType, SlotSize] = Map(
    LARGE_VEHICLE -> SlotSize(1, LARGE_SPACE),
    MIDDLE_VEHICLE -> SlotSize(1, MIDDLE_SPACE),
    SMALL_VEHICLE -> SlotSize(1, SMALL_SPACE)
  )

  val originalSizeSlotsMapping: Map[SlotSize, List[Slot]] = Map(
    SlotSize(1, LARGE_SPACE) -> largeSlots,
    SlotSize(1, MIDDLE_SPACE) -> middleSlots,
    SlotSize(1, SMALL_SPACE) -> smallSlots
  )


  def park(vehicle: Vehicle): Option[Ticket] = {
    findSuitableSlot(vehicle) match {
      case Some(slot) =>
        originalSizeSlotsMapping(slot.originalSize).find(_ == slot) match {
          case Some(slot) =>
            slot.availableSize = slot.availableSize - SlotSize(1, vehicleSpace(vehicle.vehicleType))
            Some(Ticket(slot))
          case _ => None
        }
      case _ => None
    }
  }


  private def findSuitableSlot(vehicle: Vehicle): Option[Slot] = {
    val slotsForSearch: List[Slot] = vehicle match {
      case Vehicle(LARGE_VEHICLE) => largeSlots
      case Vehicle(MIDDLE_VEHICLE) => middleSlots ++ largeSlots
      case Vehicle(SMALL_VEHICLE) => smallSlots ++ largeSlots.filter(_.availableSize == SlotSize(1, SMALL_SPACE)) ++ middleSlots ++ largeSlots
      case _ => throw new UnknownVehicleType(vehicle)
    }
    slotsForSearch.filter(_.availableSize.number != 0)
      .find(_.availableSize >= SlotSize(1, vehicleSpace(vehicle.vehicleType)))
  }

  val vehicleSpace: Map[VehicleType, SlotSpace] = Map(
    LARGE_VEHICLE -> LARGE_SPACE,
    MIDDLE_VEHICLE -> MIDDLE_SPACE,
    SMALL_VEHICLE -> SMALL_SPACE
  )
}

object ParkingSlotManager {
  private val largeSlots: List[Slot] = List(Slot(1, SlotSize(1, LARGE_SPACE)), Slot(2, SlotSize(1, LARGE_SPACE)))
  private val middleSlots: List[Slot] = List(Slot(3, SlotSize(1, MIDDLE_SPACE)), Slot(4, SlotSize(1, MIDDLE_SPACE)), Slot(5, SlotSize(1, MIDDLE_SPACE)))
  private val smallSlots: List[Slot] = List(Slot(6, SlotSize(1, SMALL_SPACE)), Slot(7, SlotSize(1, SMALL_SPACE)), Slot(8, SlotSize(1, SMALL_SPACE)))

  private val parkingSlotManager: ParkingSlotManager = new ParkingSlotManager(largeSlots, middleSlots, smallSlots)

  val park = parkingSlotManager.park(_)

}
