package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** メジャーシックス（長三和音に長6度を加えた和音）を表す。 */
final case class MajorSixth(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] = MajorTriad(root).chordTones

  val nonChordTones: List[Interval] = List(Interval.majorSixth)
