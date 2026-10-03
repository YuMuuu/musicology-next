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

  /** 1〜8度を重減〜重増まで定義した音程の一覧
    *
    * 各セルは `Interval(fifths, octave)` の引数を表す。— は該当する音程がないことを示す
    * 完全系（1・4・5・8度）は各5種類、長短系（2・3・6・7度）は各6種類で、形式的には44種類
    *
    * | 度数 | 重増 | 増 | 完全 | 長 | 短 | 減 | 重減 |
    * | --- | --- | --- | --- | --- | --- | --- | --- |
    * | 1 | (14, -8) | (7, -4) | (0, 0) | — | — | (-7, 4) | (-14, 8) |
    * | 2 | (16, -9) | (9, -5) | — | (2, -1) | (-5, 3) | (-12, 7) | (-19, 11) |
    * | 3 | (18, -10) | (11, -6) | — | (4, -2) | (-3, 2) | (-10, 6) | (-17, 10) |
    * | 4 | (13, -7) | (6, -3) | (-1, 1) | — | — | (-8, 5) | (-15, 9) |
    * | 5 | (15, -8) | (8, -4) | (1, 0) | — | — | (-6, 4) | (-13, 8) |
    * | 6 | (17, -9) | (10, -5) | — | (3, -1) | (-4, 3) | (-11, 7) | (-18, 11) |
    * | 7 | (19, -10) | (12, -6) | — | (5, -2) | (-2, 2) | (-9, 6) | (-16, 10) |
    * | 8 | (14, -7) | (7, -3) | (0, 1) | — | — | (-7, 5) | (-14, 9) |
    *
    */
  def doubleAugmentedUnison: Interval = Interval(14, -8) // 重増1度
  def augmentedUnison: Interval = Interval(7, -4) // 増1度
  def perfectUnison: Interval = Interval(0, 0) // 完全1度
  def diminishedUnison: Interval = Interval(-7, 4) // 減1度 ※認めない体系もあるよう
  def doubleDiminishedUnison: Interval = Interval(-14, 8) // 重減1度 ※認めない体系もあるよう

  def doubleAugmentedSecond: Interval = Interval(16, -9) // 重増2度
  def augmentedSecond: Interval = Interval(9, -5) // 増2度
  def majorSecond: Interval = Interval(2, -1) // 長2度
  def minorSecond: Interval = Interval(-5, 3) // 短2度
  def diminishedSecond: Interval = Interval(-12, 7) // 減2度
  def doubleDiminishedSecond: Interval = Interval(-19, 11) // 重減2度

  def doubleAugmentedThird: Interval = Interval(18, -10) // 重増3度
  def augmentedThird: Interval = Interval(11, -6) // 増3度
  def majorThird: Interval = Interval(4, -2) // 長3度
  def minorThird: Interval = Interval(-3, 2) // 短3度
  def diminishedThird: Interval = Interval(-10, 6) // 減3度
  def doubleDiminishedThird: Interval = Interval(-17, 10) // 重減3度

  def doubleAugmentedFourth: Interval = Interval(13, -7) // 重増4度
  def augmentedFourth: Interval = Interval(6, -3) // 増4度
  def perfectFourth: Interval = Interval(-1, 1) // 完全4度
  def diminishedFourth: Interval = Interval(-8, 5) // 減4度
  def doubleDiminishedFourth: Interval = Interval(-15, 9) // 重減4度

  def doubleAugmentedFifth: Interval = Interval(15, -8) // 重増5度
  def augmentedFifth: Interval = Interval(8, -4) // 増5度
  def perfectFifth: Interval = Interval(1, 0) // 完全5度
  def diminishedFifth: Interval = Interval(-6, 4) // 減5度
  def doubleDiminishedFifth: Interval = Interval(-13, 8) // 重減5度

  def doubleAugmentedSixth: Interval = Interval(17, -9) // 重増6度
  def augmentedSixth: Interval = Interval(10, -5) // 増6度
  def majorSixth: Interval = Interval(3, -1) // 長6度
  def minorSixth: Interval = Interval(-4, 3) // 短6度
  def diminishedSixth: Interval = Interval(-11, 7) // 減6度
  def doubleDiminishedSixth: Interval = Interval(-18, 11) // 重減6度

  def doubleAugmentedSeventh: Interval = Interval(19, -10) // 重増7度
  def augmentedSeventh: Interval = Interval(12, -6) // 増7度
  def majorSeventh: Interval = Interval(5, -2) // 長7度
  def minorSeventh: Interval = Interval(-2, 2) // 短7度
  def diminishedSeventh: Interval = Interval(-9, 6) // 減7度
  def doubleDiminishedSeventh: Interval = Interval(-16, 10) // 重減7度

  def doubleAugmentedOctave: Interval = Interval(14, -7) // 重増8度
  def augmentedOctave: Interval = Interval(7, -3) // 増8度
  def perfectOctave: Interval = Interval(0, 1) // 完全8度
  def diminishedOctave: Interval = Interval(-7, 5) // 減8度
  def doubleDiminishedOctave: Interval = Interval(-14, 9) // 重減8度
