Parking Slot Problem in Scala
-------------------

This is a Parking Slot Scala solution.

### the problem
**Parking Slot**

* A Parking place has lots of slots. 
* There are four different types of slot: Large, Middle, Small, Tiny.
* There are four different types of vehicles: Large, Middle, Small, Tiny.

The problem is to park the car with following logics:
* A large vehicle can only be parked in the large slot
* A middle vehicle can be parked in the middle or large slot
* A small vehicle can be parked in the small slot, middle slot and large slot.
A large slot can afford two small vehicle. A small vehicle should be first parked into a
large slot which has already parked one small vehicle. Then the small slot is considered, then the middle slot.
* A tiny vehicle can be parked int the tiny slot, small slot, middle slot and large slot. A large slot can afford 3
tiny vehicle. A tiny vehicle should be parked to the large slot which has two tiny vehicle, and that has one tiny slot,
then the tiny slot, then the small slot, then the middle slot, and then the large slot

###  环境
* scala 2.11.8
* sbt 0.13.8

### 说明
* 入口文件为MainApp
* 具体的类的文档请参考该类的测试

