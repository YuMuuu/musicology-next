// Source: https://github.com/YuMuuu/musicology/blob/master/src/main/scala/note/Pitch.scala
package dev.yumuuu.musicology.domain.note

/** 絶対的な音程を表す / 音程の操作を表す
  *
  * @param fifths
  *   c4から何回五度上に移動したか
  * @param octave
  *   fifthsからoctave上に何回移動したか
  */
final case class Pitch(fifths: Int, octave: Int):
  def +(interval: Interval): Pitch =
    Pitch(this.fifths + interval.fifths, this.octave + interval.octave)

  def -(pitch: Pitch): Interval =
    Interval(this.fifths - pitch.fifths, this.octave - pitch.octave)

  def midiNoteNumber(): Int = 60 + fifths * 7 + octave * 12

  def sharp(): Pitch = this + Interval(7, -4)

  //fを定義してflatMapにしたい
  def flat(): Pitch = this + Interval(-7, 4)

object Pitch:
  val dbb4 = Pitch(-12, 7)
  val abb4 = Pitch(-11, 7)
  val ebb4 = Pitch(-10, 6)
  val bbb4 = Pitch(-9, 6)
  val fb4 = Pitch(-8, 5)
  val cb4 = Pitch(-7, 4)
  val gb4 = Pitch(-6, 4)
  val db4 = Pitch(-5, 3)
  val ab4 = Pitch(-4, 3)
  val eb4 = Pitch(-3, 2)
  val bb4 = Pitch(-2, 2)
  val f4 = Pitch(-1, 1)

  val c4 = Pitch(0, 0) //基準音

  val g4 = Pitch(1, 0)
  val d4 = Pitch(2, -1)
  val a4 = Pitch(3, -1)
  val e4 = Pitch(4, -2)
  val b4 = Pitch(5, -2)
  val fs4 = Pitch(6, -3)
  val cs4 = Pitch(7, -4)
  val gs4 = Pitch(8, -4)
  val ds4 = Pitch(9, -5)
  val as4 = Pitch(10, -5)
  val es4 = Pitch(11, -6)
  val bs4 = Pitch(12, -6)
//memo: 必要になったら足す
