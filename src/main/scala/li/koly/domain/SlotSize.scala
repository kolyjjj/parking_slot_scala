package li.koly.domain

import li.koly.exception.{CannotBeSubtracted, NoConversionToLevel}

// 1 large slot equals 3 tiny slots, 1 large - 1 tiny = 2 tinys
// 2 middle slot equals 2 tiny slots, 2 middle - 1 tiny = 1 tiny
case class SlotSize(number: Int, slotSpace: SlotSpace) {
  def this(size: SlotSize) = this(size.number, size.slotSpace)

  def -(size: SlotSize): SlotSize = {
    val result = subtract(this, size)
    if (result.number < 0) throw new CannotBeSubtracted(size.toString) else result
  }

  def >=(size: SlotSize): Boolean = {
    try {
      this.slotSpace.compareTo(size.slotSpace) match {
        case 0 => this.number >= size.number
        case 1 => convertToLevel(this, size.slotSpace).number >= size.number
        case -1 => convertToLevel(size, this.slotSpace).number > this.number
      }
    } catch {
      case _: RuntimeException => false
    }
  }

  private def canBeConverted(a: SlotSize, b: SlotSpace): Boolean = {
    try {
      convertToLevel(a, b)
      true
    } catch {
      case _: NoConversionToLevel => false
    }
  }


  private def subtract(a: SlotSize, b: SlotSize): SlotSize = {
    a.slotSpace.compareTo(b.slotSpace) match {
      case 0 => SlotSize(a.number - b.number, b.slotSpace)
      case 1 => SlotSize(convertToLevel(a, b.slotSpace).number - b.number, b.slotSpace)
      case -1 =>
        val temp = subtract(b, a)
        SlotSize(-temp.number, temp.slotSpace)
    }
  }

  private def convertToLevel(size: SlotSize, space: SlotSpace): SlotSize = (size, space) match {
    case (SlotSize(num, LARGE_SPACE), SMALL_SPACE) => SlotSize(num * 2, SMALL_SPACE)
    case (SlotSize(num, LARGE_SPACE), MIDDLE_SPACE) => SlotSize(num * 1, MIDDLE_SPACE)
    case (SlotSize(num, MIDDLE_SPACE), SMALL_SPACE) => SlotSize(num * 1, SMALL_SPACE)
    case _ => throw new NoConversionToLevel(size, space)
  }
}

object SlotSize {
  def apply(size: SlotSize) = new SlotSize(size)
}
