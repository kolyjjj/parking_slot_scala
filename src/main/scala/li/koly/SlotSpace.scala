package li.koly

sealed class SlotSpace
case object LARGE_SPACE extends SlotSpace
case object MIDDLE_SPACE extends SlotSpace
case object SMALL_SPACE extends SlotSpace
case object TINY_SPACE extends SlotSpace
case object EMPTY_SPACE extends SlotSpace
