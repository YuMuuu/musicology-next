package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

trait Chord:
  // TODO: 将来的には fifths のみを持つ PitchClass を定義し、root をオクターブに依存しない型にする。
  def root: Pitch

  /** root・3rd・5th・7th のうち、コードが持つ構成和音を root からの音程で表す。 */
  def chordTones: NonEmptyList[Interval]

  /** root・3rd・5th・7th 以外の非構成和音を root からの音程で表す。 */
  def nonChordTones: List[Interval]
