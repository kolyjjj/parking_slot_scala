package li.koly.domain

sealed abstract class SlotSpace extends Comparable[SlotSpace] {

  override def compareTo(slotSpace: SlotSpace): Int = (this, slotSpace) match {
    case (LARGE_SPACE, LARGE_SPACE) => 0
    case (MIDDLE_SPACE, MIDDLE_SPACE) => 0
    case (SMALL_SPACE, SMALL_SPACE) => 0
    case (LARGE_SPACE, MIDDLE_SPACE) => 1
    case (LARGE_SPACE, SMALL_SPACE) => 1
    case (MIDDLE_SPACE, SMALL_SPACE) => 1
    case _ => -1
  }

  def >=(slotSpace: SlotSpace): Boolean = this.compareTo(slotSpace) >= 0

  def <=(slotSpace: SlotSpace) = this.compareTo(slotSpace) < 1

  def <(slotSpace: SlotSpace) = this.compareTo(slotSpace) == -1

}

case object LARGE_SPACE extends SlotSpace

case object MIDDLE_SPACE extends SlotSpace

case object SMALL_SPACE extends SlotSpace

case object TINY_SPACE extends SlotSpace

case object EMPTY_SPACE extends SlotSpace
