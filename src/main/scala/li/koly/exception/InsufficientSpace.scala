package li.koly.exception

import li.koly.domain.{SlotSize, SlotSpace}

class InsufficientSpace(slotSize: SlotSize, slotSpace: SlotSpace) extends RuntimeException(slotSize.toString + " - " + slotSpace.toString)
