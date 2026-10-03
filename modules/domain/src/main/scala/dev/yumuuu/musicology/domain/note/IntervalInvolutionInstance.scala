package dev.yumuuu.musicology.domain.note

import dev.yumuuu.musicology.domain.algebra.Involution

/** 完全8度を基準とする音程の転回写像。
  *
  * value + invert(value) == Interval.oneOctaveUp を満たす。 複音程も正規化せず、有向音程として返す。
  */
given IntervalInvolutionInstance: Involution[Interval] with
  override def invert(value: Interval): Interval = Interval.oneOctaveUp - value
