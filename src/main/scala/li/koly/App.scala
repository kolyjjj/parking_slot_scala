package li.koly

class MainApp extends App{
  private val ticket: Ticket = ParkingSlotManager.park(Vehicle(LARGE_VEHICLE))
  Console println ticket.toString
}
