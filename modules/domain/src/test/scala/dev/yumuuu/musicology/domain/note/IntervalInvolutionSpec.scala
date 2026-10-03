package dev.yumuuu.musicology.domain.note

import cats.Eq
import dev.yumuuu.musicology.domain.algebra.Involution
import org.scalacheck.Gen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

final class IntervalInvolutionSpec extends AnyFlatSpec with ScalaCheckPropertyChecks:
  private val inversion = Involution[Interval]
  private val intervalEq = Eq.fromUniversalEquals[Interval]
  private val intervalsEq = Eq.fromUniversalEquals[List[Interval]]
  private val intervalGen =
    for
      fifths <- Gen.choose(-100, 100)
      octave <- Gen.choose(-100, 100)
    yield Interval(fifths, octave)

  private val upperPitches =
    List(Pitch.e4, Pitch.g4, Pitch.fs4, Pitch.fss4, Pitch.c4 + Interval.oneOctaveUp)

  "IntervalInversion.invert" should "低い音を1オクターブ上げた二音間の音程を返す" in:
    val lower = Pitch.c4
    assert(upperPitches.forall { upper =>
      intervalEq.eqv(
        IntervalInversion.invert(upper - lower),
        (lower + Interval.oneOctaveUp) - upper
      )
    })

  "IntervalInversion.invert" should "高い音を1オクターブ下げた二音間の音程を返す" in:
    val lower = Pitch.c4
    assert(upperPitches.forall { upper =>
      intervalEq.eqv(
        IntervalInversion.invert(upper - lower),
        lower - (upper + Interval.oneOctaveDown)
      )
    })

  "IntervalInversion.invert" should "Involutionの写像と一致する" in:
    forAll(intervalGen) { value =>
      assert(intervalEq.eqv(IntervalInversion.invert(value), inversion.invert(value)))
    }

  "音程の転回写像" should "二度適用すると元の音程に戻る" in:
    forAll(intervalGen) { value =>
      assert(intervalEq.eqv(inversion.invert(inversion.invert(value)), value))
    }

  "音程の転回写像" should "元の音程と転回音程の和が完全8度になる" in:
    forAll(intervalGen) { value =>
      assert(intervalEq.eqv(value + inversion.invert(value), Interval.oneOctaveUp))
    }

  "音程の転回写像" should "完全1度と完全8度を交換する" in:
    assert(
      intervalsEq.eqv(
        List(Interval.unit, Interval.oneOctaveUp).map(inversion.invert),
        List(Interval.oneOctaveUp, Interval.unit)
      )
    )

  "音程の転回写像" should "長短・完全・増減・重増重減の関係を保つ" in:
    val originals = List(
      Interval(2, -1), // 長2度
      Interval(4, -2), // 長3度
      Interval(-3, 2), // 短3度
      Interval(-1, 1), // 完全4度
      Interval(6, -3), // 増4度
      Interval(-8, 5), // 減4度
      Interval(13, -7), // 重増4度
      Interval(-15, 9) // 重減4度
    )
    val inverted = List(
      Interval(-2, 2), // 短7度
      Interval(-4, 3), // 短6度
      Interval(3, -1), // 長6度
      Interval(1, 0), // 完全5度
      Interval(-6, 4), // 減5度
      Interval(8, -4), // 増5度
      Interval(-13, 8), // 重減5度
      Interval(15, -8) // 重増5度
    )
    assert(intervalsEq.eqv(originals.map(inversion.invert), inverted))

  "音程の転回写像" should "下行音程と複音程をオクターブ正規化せず写す" in:
    assert(
      intervalsEq.eqv(
        List(Interval(-4, 2), Interval(2, 0)).map(inversion.invert),
        List(Interval(4, -1), Interval(-2, 1))
      )
    )
