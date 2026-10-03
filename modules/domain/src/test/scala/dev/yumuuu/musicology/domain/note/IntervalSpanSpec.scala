package dev.yumuuu.musicology.domain.note

import cats.Eq
import org.scalatest.flatspec.AnyFlatSpec

final class IntervalSpanSpec extends AnyFlatSpec:
  private val spanEq = Eq.fromUniversalEquals[IntervalSpan]

  "音程の分類" should "上行・下行とも1〜7度をSingleIntervalとする" in:
    assert(
      List(
        Interval.unit,
        Interval(1, 0),
        Interval(-1, 0),
        Interval(5, -2),
        Interval(-5, 2)
      ).forall { interval =>
        spanEq.eqv(IntervalSpan.span(interval), IntervalSpan.SingleInterval)
      }
    )

  "音程の分類" should "上行・下行とも8度以上をDoubleIntervalとする" in:
    assert(
      List(
        Interval.oneOctaveUp,
        Interval.oneOctaveDown,
        Interval(2, 0),
        Interval(-2, 0),
        Interval(0, 2),
        Interval(0, -2)
      )
        .forall(interval => spanEq.eqv(IntervalSpan.span(interval), IntervalSpan.DoubleInterval))
    )

  "音程の分類" should "増7度と減8度を半音数ではなく度数で分類する" in:
    assert(
      spanEq.eqv(IntervalSpan.span(Interval(12, -6)), IntervalSpan.SingleInterval) &&
        spanEq.eqv(IntervalSpan.span(Interval(-12, 6)), IntervalSpan.SingleInterval) &&
        spanEq.eqv(IntervalSpan.span(Interval(-7, 5)), IntervalSpan.DoubleInterval) &&
        spanEq.eqv(IntervalSpan.span(Interval(7, -5)), IntervalSpan.DoubleInterval)
    )
