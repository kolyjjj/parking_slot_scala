package li.koly.domain

case class Slot(slotId: Int, originalSize: SlotSize, var availableSize: SlotSize) {
  def this(id: Int, availableSize: SlotSize) = this(id, availableSize, availableSize)
}

object Slot {
  def apply(id: Int, availableSize: SlotSize) = new Slot(id, availableSize)
}