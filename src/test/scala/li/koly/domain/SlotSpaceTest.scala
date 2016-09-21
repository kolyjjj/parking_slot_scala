package li.koly.domain

import org.scalatest.FunSpec

class SlotSpaceTest extends FunSpec {
  describe("slot space") {
    it("larger than or equal to") {
      assert(LARGE_SPACE >= MIDDLE_SPACE)
      assert(LARGE_SPACE >= SMALL_SPACE)
      assert(MIDDLE_SPACE >= SMALL_SPACE)
      assert(LARGE_SPACE >= LARGE_SPACE)
      assert(MIDDLE_SPACE >= MIDDLE_SPACE)
      assert(SMALL_SPACE >= SMALL_SPACE)

      assert((MIDDLE_SPACE >= LARGE_SPACE) == false)
      assert((SMALL_SPACE >= LARGE_SPACE) == false)
      assert((SMALL_SPACE >= MIDDLE_SPACE) == false)
    }

    it("smaller than or equal to") {
      assert(MIDDLE_SPACE <= LARGE_SPACE)
      assert(SMALL_SPACE <= LARGE_SPACE)
      assert(SMALL_SPACE <= MIDDLE_SPACE)
      assert(LARGE_SPACE <= LARGE_SPACE)
      assert(MIDDLE_SPACE <= MIDDLE_SPACE)
      assert(SMALL_SPACE <= SMALL_SPACE)

      assert((LARGE_SPACE <= MIDDLE_SPACE) == false)
      assert((LARGE_SPACE <= SMALL_SPACE) == false)
      assert((MIDDLE_SPACE <= SMALL_SPACE) == false)
    }

    it("smaller than") {
      assert(MIDDLE_SPACE < LARGE_SPACE)
      assert(SMALL_SPACE < LARGE_SPACE)
      assert(SMALL_SPACE < MIDDLE_SPACE)

      assert((LARGE_SPACE < LARGE_SPACE) == false)
      assert((MIDDLE_SPACE < MIDDLE_SPACE) == false)
      assert((SMALL_SPACE < SMALL_SPACE) == false)
      assert((LARGE_SPACE < MIDDLE_SPACE) == false)
      assert((LARGE_SPACE < SMALL_SPACE) == false)
      assert((MIDDLE_SPACE < SMALL_SPACE) == false)
    }

    it("compare to") {
      assert(LARGE_SPACE.compareTo(LARGE_SPACE) == 0)
      assert(MIDDLE_SPACE.compareTo(MIDDLE_SPACE) == 0)
      assert(SMALL_SPACE.compareTo(SMALL_SPACE) == 0)
      assert(LARGE_SPACE.compareTo(MIDDLE_SPACE) == 1)
      assert(LARGE_SPACE.compareTo(SMALL_SPACE) == 1)
      assert(MIDDLE_SPACE.compareTo(SMALL_SPACE) == 1)
      assert(MIDDLE_SPACE.compareTo(LARGE_SPACE) == -1)
      assert(SMALL_SPACE.compareTo(LARGE_SPACE) == -1)
      assert(SMALL_SPACE.compareTo(MIDDLE_SPACE) == -1)
    }
  }
}
