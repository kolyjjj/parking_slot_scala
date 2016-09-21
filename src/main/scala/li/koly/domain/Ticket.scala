package li.koly.domain

case class Ticket(slot: Slot) {
  def prettyPrint(): String = "Slot: " + slot.slotId + " size: " + slot.originalSize.slotSpace.toString
}
