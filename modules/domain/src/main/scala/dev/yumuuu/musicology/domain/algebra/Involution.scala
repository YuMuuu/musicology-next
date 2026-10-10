package dev.yumuuu.musicology.domain.algebra

/** 二度適用すると元の値に戻る写像。
  *
  * インスタンスは invert(invert(value)) == value を満たす。
  */
trait Involution[A]:
  def invert(value: A): A

object Involution:
  def apply[A](using instance: Involution[A]): Involution[A] = instance
