package dev.yumuuu.musicology.domain.note

/** 全音を単位とする音程の幅をStepとする。
  *
  * 例:
  * - C→Dは 1 step
  * - C→C♯は 1/2 step
  */
object Step:
  /** Intervalから方向付きのstep数を返す。音高の上行は正、下行は負、同じ音高は0となる。 */
  def step(interval: Interval): Double = (interval.fifths * 7 + interval.octave * 12) / 2.0
