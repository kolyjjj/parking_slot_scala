package li.koly.exception

import li.koly.domain.Vehicle

class NoSuitableSlotFound(vehicle: Vehicle) extends RuntimeException(vehicle.toString)
