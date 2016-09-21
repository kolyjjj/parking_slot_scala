package li.koly.exception

import li.koly.domain.{SlotSize, SlotSpace}

class NoConversionToLevel(size: SlotSize, space: SlotSpace) extends RuntimeException(size.toString + " - " + space.toString)
