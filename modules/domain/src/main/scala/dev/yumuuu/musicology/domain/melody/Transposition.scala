package dev.yumuuu.musicology.domain.melody

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

object Transposition:
  /** メロディの各音を指定した音程だけ移調する。
    *
    * 現在のモデルではメロディの音高と順序のみを扱い、音価は考慮しない。
    */
  def transpose(melody: NonEmptyList[Pitch], interval: Interval): NonEmptyList[Pitch] =
    melody.map(_ + interval)
