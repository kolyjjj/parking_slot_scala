package li.koly

object ParkingSlotManager {
  val largeSlots: List[Slot] = List(Slot(1, LARGE_SPACE), Slot(2, LARGE_SPACE))
  val middleSlots: List[Slot] = List(Slot(3, MIDDLE_SPACE), Slot(4, MIDDLE_SPACE), Slot(5, MIDDLE_SPACE))

  val vehicleSlotsMapping: Map[VehicleType, List[Slot]] = Map(
    LARGE_VEHICLE -> largeSlots,
    MIDDLE_VEHICLE -> middleSlots
  )


  def park(vehicle: Vehicle): Ticket = {


    val slots: List[Slot] = vehicleSlotsMapping(vehicle.vehicleType)
    val slot: Slot = findSlot(slots, vehicle)
    slots.dropWhile(_.number == slot.number)
    Slot(slot.number, calculateSpace(slot.space, vehicle.vehicleType))
    Ticket(slot)

  }

  private def findSlot(slots: List[Slot], vehicle: Vehicle): Slot = {
    slots.filter(slot => isAvailable(slot) && isAffordable(slot, vehicle))(0)
  }

  private def calculateSpace(space: SlotSpace, vehicleType: VehicleType): SlotSpace = ???


  private val isAvailable: PartialFunction[Slot, Boolean] = {
    case Slot(_, EMPTY_SPACE) => false
    case _ => true
  }

  private def isAffordable(slot: Slot, vehicle: Vehicle): Boolean = {
    slot.space == LARGE_SPACE && vehicle.vehicleType == LARGE_VEHICLE ||
    slot.space == MIDDLE_SPACE && vehicle.vehicleType == MIDDLE_VEHICLE
  }

}
