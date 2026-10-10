package dev.yumuuu.musicology.domain.note

import dev.yumuuu.musicology.domain.algebra.Involution

object IntervalInversion:
  /** 低い音を1オクターブ上げる、または高い音を1オクターブ下げる転回音程を返す。
    *
    * どちらの操作も、完全8度から元の音程を引いた同じ音程になる。
    */
  def invert(interval: Interval): Interval = Involution[Interval].invert(interval)
