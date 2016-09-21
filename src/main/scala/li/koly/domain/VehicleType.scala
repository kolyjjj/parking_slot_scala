package li.koly.domain

sealed class VehicleType

case object LARGE_VEHICLE extends VehicleType

case object MIDDLE_VEHICLE extends VehicleType

case object SMALL_VEHICLE extends VehicleType

case object TINY_VEHICLE extends VehicleType
