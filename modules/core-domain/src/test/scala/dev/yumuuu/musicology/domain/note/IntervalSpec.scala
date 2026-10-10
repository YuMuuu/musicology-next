package dev.yumuuu.musicology.domain.note

import cats.Eq
import org.scalatest.flatspec.AnyFlatSpec

final class IntervalSpec extends AnyFlatSpec:
  private val intervalEq = Eq.fromUniversalEquals[Interval]

  "音程の加法" should "五度とオクターブをそれぞれ加算する" in:
    assert(intervalEq.eqv(Interval(1, 2) + Interval(3, 4), Interval(4, 6)))

  "音程の減法" should "五度とオクターブをそれぞれ減算する" in:
    assert(intervalEq.eqv(Interval(4, 6) - Interval(1, 2), Interval(3, 4)))

  "音程の五度成分比較" should "五度成分が同じか判定する" in:
    assert(Interval(1, 2).isSameFifths(Interval(1, 3)))

  // "音程の正規化" should "正負の五度成分をまとめてオクターブへ移す" in:
  //   def preservesInterval(original: Interval, expected: Interval): Boolean =
  //     val normalized = original.normalize
  //     intervalEq.eqv(normalized, expected) &&
  //     intervalEq.eqv(normalized.normalize, normalized) &&
  //     Eq[Int].eqv(
  //       original.fifths * 7 + original.octave * 12,
  //       normalized.fifths * 7 + normalized.octave * 12
  //     ) &&
  //     intervalEq.eqv(
  //       (Interval.unit - original).normalize,
  //       Interval.unit - normalized
  //     )
  //
  //   assert(
  //     preservesInterval(Interval(12, 0), Interval(0, 7)) &&
  //       preservesInterval(Interval(25, -3), Interval(1, 11)) &&
  //       preservesInterval(Interval(-12, 7), Interval.unit) &&
  //       preservesInterval(Interval(-12, 0), Interval(0, -7)) &&
  //       preservesInterval(Interval(-1, 0), Interval(-1, 0)) &&
  //       preservesInterval(Interval(-25, 3), Interval(-1, -11)) &&
  //       preservesInterval(Interval(-11, 2), Interval(-11, 2)) &&
  //       preservesInterval(Interval(0, 2), Interval(0, 2)) &&
  //       preservesInterval(Interval(11, -2), Interval(11, -2))
  //   )

  "音程の定義" should "全44種類の度数と半音数を表す" in:
    def verify(value: Interval, steps: Int, semitones: Int): Unit =
      assert(Eq[Int].eqv(value.fifths * 4 + value.octave * 7, steps))
      assert(Eq[Int].eqv(value.fifths * 7 + value.octave * 12, semitones))

    verify(Interval.doubleAugmentedUnison, 0, 2)
    verify(Interval.augmentedUnison, 0, 1)
    verify(Interval.perfectUnison, 0, 0)
    verify(Interval.diminishedUnison, 0, -1)
    verify(Interval.doubleDiminishedUnison, 0, -2)
    verify(Interval.doubleAugmentedSecond, 1, 4)
    verify(Interval.augmentedSecond, 1, 3)
    verify(Interval.majorSecond, 1, 2)
    verify(Interval.minorSecond, 1, 1)
    verify(Interval.diminishedSecond, 1, 0)
    verify(Interval.doubleDiminishedSecond, 1, -1)
    verify(Interval.doubleAugmentedThird, 2, 6)
    verify(Interval.augmentedThird, 2, 5)
    verify(Interval.majorThird, 2, 4)
    verify(Interval.minorThird, 2, 3)
    verify(Interval.diminishedThird, 2, 2)
    verify(Interval.doubleDiminishedThird, 2, 1)
    verify(Interval.doubleAugmentedFourth, 3, 7)
    verify(Interval.augmentedFourth, 3, 6)
    verify(Interval.perfectFourth, 3, 5)
    verify(Interval.diminishedFourth, 3, 4)
    verify(Interval.doubleDiminishedFourth, 3, 3)
    verify(Interval.doubleAugmentedFifth, 4, 9)
    verify(Interval.augmentedFifth, 4, 8)
    verify(Interval.perfectFifth, 4, 7)
    verify(Interval.diminishedFifth, 4, 6)
    verify(Interval.doubleDiminishedFifth, 4, 5)
    verify(Interval.doubleAugmentedSixth, 5, 11)
    verify(Interval.augmentedSixth, 5, 10)
    verify(Interval.majorSixth, 5, 9)
    verify(Interval.minorSixth, 5, 8)
    verify(Interval.diminishedSixth, 5, 7)
    verify(Interval.doubleDiminishedSixth, 5, 6)
    verify(Interval.doubleAugmentedSeventh, 6, 13)
    verify(Interval.augmentedSeventh, 6, 12)
    verify(Interval.majorSeventh, 6, 11)
    verify(Interval.minorSeventh, 6, 10)
    verify(Interval.diminishedSeventh, 6, 9)
    verify(Interval.doubleDiminishedSeventh, 6, 8)
    verify(Interval.doubleAugmentedOctave, 7, 14)
    verify(Interval.augmentedOctave, 7, 13)
    verify(Interval.perfectOctave, 7, 12)
    verify(Interval.diminishedOctave, 7, 11)
    verify(Interval.doubleDiminishedOctave, 7, 10)
