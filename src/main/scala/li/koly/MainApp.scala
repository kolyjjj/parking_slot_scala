package li.koly

import li.koly.domain._
import li.koly.service.ParkingSlotManager

object MainApp extends App {

  private val vehicles: List[Vehicle] = List(
    Vehicle(LARGE_VEHICLE),
    Vehicle(MIDDLE_VEHICLE),
    Vehicle(SMALL_VEHICLE),
    Vehicle(MIDDLE_VEHICLE),
    Vehicle(MIDDLE_VEHICLE),
    Vehicle(SMALL_VEHICLE),
    Vehicle(SMALL_VEHICLE),
    Vehicle(SMALL_VEHICLE),
    Vehicle(MIDDLE_VEHICLE)
  )
  vehicles.foreach {
    v =>
      Console println (
        ParkingSlotManager park v match {
          case Some(t) => v.vehicleType + "  " + t.prettyPrint
          case _ => "No slot for your vehicle " + v.vehicleType
        }
        )
  }
}
