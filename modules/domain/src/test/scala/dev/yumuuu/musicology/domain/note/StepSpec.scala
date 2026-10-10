package dev.yumuuu.musicology.domain.note

import cats.Eq
import org.scalatest.flatspec.AnyFlatSpec

final class StepSpec extends AnyFlatSpec:
  "全音を単位とするstep数" should "半音を1/2、全音を1として返す" in:
    assert(
      Eq[Double].eqv(Step.step(Interval.perfectUnison), 0.0) &&
        Eq[Double].eqv(Step.step(Interval.minorSecond), 1.0 / 2.0) &&
        Eq[Double].eqv(Step.step(Interval.majorSecond), 1.0) &&
        Eq[Double].eqv(Step.step(Interval.minorThird), 3.0 / 2.0) &&
        Eq[Double].eqv(Step.step(Interval.majorThird), 2.0) &&
        Eq[Double].eqv(Step.step(Interval.perfectOctave), 6.0) &&
        Eq[Double].eqv(Step.step(Interval.majorSecond + Interval.oneOctaveUp), 7.0)
    )

  it should "音高の下行を負のstep数で表す" in:
    assert(
      Eq[Double].eqv(Step.step(Interval.unit - Interval.minorSecond), -1.0 / 2.0) &&
        Eq[Double].eqv(Step.step(Interval.unit - Interval.majorThird), -2.0) &&
        Eq[Double].eqv(Step.step(Interval.oneOctaveDown), -6.0) &&
        Eq[Double].eqv(Step.step(Interval.diminishedUnison), -1.0 / 2.0) &&
        Eq[Double].eqv(Step.step(Interval.doubleDiminishedUnison), -1.0)
    )

  it should "異名同音の音程に同じstep数を返す" in:
    assert(
      Eq[Double].eqv(Step.step(Interval.augmentedUnison), Step.step(Interval.minorSecond)) &&
        Eq[Double].eqv(Step.step(Interval.augmentedSeventh), 6.0) &&
        Eq[Double].eqv(Step.step(Interval.diminishedOctave), 11.0 / 2.0)
    )
