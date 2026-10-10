package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** メジャートライアド（長三和音）を表す。 */
final case class MajorTriad(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] =
    NonEmptyList.of(Interval.unis, Interval.majorThird, Interval.perfectFifth)

  val nonChordTones: List[Interval] = List.empty
