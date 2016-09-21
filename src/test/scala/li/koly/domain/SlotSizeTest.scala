package li.koly.domain

import org.scalatest.FunSpec

class SlotSizeTest extends FunSpec {
  describe("slot size") {
    it("construct from another slot size") {
      assert(SlotSize(SlotSize(1, MIDDLE_SPACE)) == SlotSize(1, MIDDLE_SPACE))
    }

    it("larger than or equal to") {
      assert(SlotSize(1, LARGE_SPACE) >= SlotSize(1, SMALL_SPACE))
      assert(SlotSize(1, LARGE_SPACE) >= SlotSize(2, SMALL_SPACE))
      assert(SlotSize(1, MIDDLE_SPACE) >= SlotSize(1, MIDDLE_SPACE))

      assert(SlotSize(1, LARGE_SPACE) >= SlotSize(1, MIDDLE_SPACE))
      assert(SlotSize(1, MIDDLE_SPACE) >= SlotSize(1, SMALL_SPACE))

      assert((SlotSize(1, MIDDLE_SPACE) >= SlotSize(1, LARGE_SPACE)) == false)
      assert((SlotSize(1, SMALL_SPACE) >= SlotSize(1, MIDDLE_SPACE)) == false)
    }

    it("subtract") {
      assert(SlotSize(2, SMALL_SPACE) - SlotSize(1, SMALL_SPACE) == SlotSize(1, SMALL_SPACE))
      assert(SlotSize(1, LARGE_SPACE) - SlotSize(1, SMALL_SPACE) == SlotSize(1, SMALL_SPACE))
      assert(SlotSize(3, SMALL_SPACE) - SlotSize(1, LARGE_SPACE) == SlotSize(1, SMALL_SPACE))

      assert(SlotSize(1, LARGE_SPACE) - SlotSize(1, MIDDLE_SPACE) == SlotSize(0, MIDDLE_SPACE))
      assert(SlotSize(1, MIDDLE_SPACE) - SlotSize(1, SMALL_SPACE) == SlotSize(0, SMALL_SPACE))
    }

    it("should throw invalid argument exception when it cannot be subtracted") {
      // TODO
    }

    it("should throw no convertion exception when it cannot be converted") {
      // TODO
    }
  }
}
