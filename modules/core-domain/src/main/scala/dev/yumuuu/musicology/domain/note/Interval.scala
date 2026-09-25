// Source: https://github.com/YuMuuu/musicology/blob/master/src/main/scala/note/Interval.scala
package dev.yumuuu.musicology.domain.note

import cats.Eq

/** 相対的な音程を表す
  *
  * @param fifths
  *   cから何回五度上に移動したか
  * @param octave
  *   fifthsからoctave上に何回移動したか
  */
final case class Interval(fifths: Int, octave: Int):
  def +(interval: Interval): Interval =
    Interval(this.fifths + interval.fifths, this.octave + interval.octave)

  def -(interval: Interval): Interval =
    Interval(this.fifths - interval.fifths, this.octave - interval.octave)

  def isSameFifths(that: Interval): Boolean = Eq[Int].eqv(this.fifths, that.fifths)

object Interval:
  val unit = Interval(0, 0) // 基準音

  val oneOctaveUp = Interval(0, 1)
  val oneOctaveDown = Interval(0, -1)
