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
