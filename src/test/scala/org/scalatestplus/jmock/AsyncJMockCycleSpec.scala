/*
 * Copyright 2001-2013 Artima, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scalatestplus.jmock

import org.scalatest._
import org.jmock.AbstractExpectations.{equal => thatEquals}
import org.scalatest.fixture


class AsyncJMockCycleSpec extends FlatSpec with Matchers {

  trait OneFish {
    def eat(food: String): Unit = ()
  }
  trait TwoFish {
    def eat(food: String): Unit = ()
  }

  "The AsyncJMockCycle trait" should "work with multiple mocks" in {

    val a = new fixture.AsyncFunSuite with AsyncJMockCycleFixture {
      test("test that should fail") { cycle =>
        import cycle._
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting { e => import e.{oneOf => OneOf}

          OneOf (oneFishMock).eat("red fish")
          OneOf (twoFishMock).eat("blue fish")
        }

        whenExecuting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("green fish")
        }

        succeed
      }

      test("test that should succeed") { cycle =>
        import cycle._
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting { e => import e.{oneOf => OneOf}

          OneOf (oneFishMock).eat("red fish")
          OneOf (twoFishMock).eat("blue fish")
        }

        whenExecuting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        succeed
      }

      test("test that should succeed with class") { cycle =>
        import cycle._
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting { e => import e.{oneOf => OneOf}

          OneOf (oneFishMock).eat("red fish")
          OneOf (twoFishMock).eat("blue fish")
        }

        whenExecuting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        succeed
      }
    }
    val rep = new EventRecordingReporter
    a.run(None, Args(rep))
    val tf = rep.testFailedEventsReceived
    tf.size should === (1)
    val ts = rep.testSucceededEventsReceived
    ts.size should === (2)
  }

  trait ThreeFish {
    def doString(food: String): Unit = ()
    def doInt(food: Int): Unit = ()
    def doShort(food: Short): Unit = ()
    def doByte(food: Byte): Unit = ()
    def doLong(food: Long): Unit = ()
    def doBoolean(food: Boolean): Unit = ()
    def doFloat(food: Float): Unit = ()
    def doDouble(food: Double): Unit = ()
    def doChar(food: Char): Unit = ()
  }

  it should "provide sugar for invoking with methods that take matchers" in {
    val a = new fixture.AsyncFunSuite with AsyncJMockCycleFixture {
      test("test that should succeed") { cycle =>
        import cycle._
        val threeFishMock = mock[ThreeFish]

        expecting { e => import e.{oneOf => OneOf, withArg}

          OneOf (threeFishMock).doString(withArg(thatEquals("red fish")))
          OneOf (threeFishMock).doInt(withArg(thatEquals(5)))
          OneOf (threeFishMock).doShort(withArg(thatEquals(5.asInstanceOf[Short])))
          OneOf (threeFishMock).doByte(withArg(thatEquals(5.asInstanceOf[Byte])))
          OneOf (threeFishMock).doLong(withArg(thatEquals(5L)))
          OneOf (threeFishMock).doBoolean(withArg(thatEquals(true)))
          OneOf (threeFishMock).doFloat(withArg(thatEquals(5.0f)))
          OneOf (threeFishMock).doDouble(withArg(thatEquals(5.0d)))
          OneOf (threeFishMock).doChar(withArg(thatEquals('5')))
        }

        whenExecuting {
          threeFishMock.doString("red fish")
          threeFishMock.doInt(5)
          threeFishMock.doShort(5)
          threeFishMock.doByte(5)
          threeFishMock.doLong(5L)
          threeFishMock.doBoolean(true)
          threeFishMock.doFloat(5.0f)
          threeFishMock.doDouble(5.0d)
          threeFishMock.doChar('5')
        }

        succeed
      }
    }
    val rep = new EventRecordingReporter
    a.run(None, Args(rep))
    val ts = rep.testSucceededEventsReceived
    ts.size should === (1)
  }

  trait FourFish {
    def doString(food: String): Unit = ()
    def doInt(food: Int): Unit = ()
    def doShort(food: Short): Unit = ()
    def doByte(food: Byte): Unit = ()
    def doLong(food: Long): Unit = ()
    def doBoolean(food: Boolean): Unit = ()
    def doFloat(food: Float): Unit = ()
    def doDouble(food: Double): Unit = ()
    def doChar(food: Char): Unit = ()
  }

  it should "provide sugar for invoking with methods that take non-matcher values" in {
    val a = new fixture.AsyncFunSuite with AsyncJMockCycleFixture {
      test("test that should succeed") { cycle =>
        import cycle._
        val fourFishMock = mock[FourFish]

        expecting { e => import e.{oneOf => OneOf, withArg}
          OneOf (fourFishMock).doString(withArg("red fish"))
          OneOf (fourFishMock).doInt(withArg(5))
          OneOf (fourFishMock).doShort(withArg(5.asInstanceOf[Short]))
          OneOf (fourFishMock).doByte(withArg(5.asInstanceOf[Byte]))
          OneOf (fourFishMock).doLong(withArg(5L))
          OneOf (fourFishMock).doBoolean(withArg(true))
          OneOf (fourFishMock).doFloat(withArg(5.0f))
          OneOf (fourFishMock).doDouble(withArg(5.0d))
          OneOf (fourFishMock).doChar(withArg('5'))
        }

        whenExecuting {
          fourFishMock.doString("red fish")
          fourFishMock.doInt(5)
          fourFishMock.doShort(5)
          fourFishMock.doByte(5)
          fourFishMock.doLong(5L)
          fourFishMock.doBoolean(true)
          fourFishMock.doFloat(5.0f)
          fourFishMock.doDouble(5.0d)
          fourFishMock.doChar('5')
        }

        succeed
      }
    }
    val rep = new EventRecordingReporter
    a.run(None, Args(rep))
    val ts = rep.testSucceededEventsReceived
    ts.size should === (1)
  }
}
