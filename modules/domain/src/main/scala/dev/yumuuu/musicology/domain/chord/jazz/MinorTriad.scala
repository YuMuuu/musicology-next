package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** マイナートライアド（短三和音）を表す。 */
final case class MinorTriad(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] =
    NonEmptyList.of(Interval.unis, Interval.minorThird, Interval.perfectFifth)

  val nonChordTones: List[Interval] = List.empty
