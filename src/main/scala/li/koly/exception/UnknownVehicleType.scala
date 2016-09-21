package li.koly.exception

import li.koly.domain.Vehicle

class UnknownVehicleType(vehicle: Vehicle) extends RuntimeException(vehicle.toString)
