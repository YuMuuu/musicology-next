package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** マイナーセブンスを表す。 */
final case class MinorSeventh(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] = NonEmptyList.of(
    Interval.unis,
    Interval.minorThird,
    Interval.perfectFifth,
    Interval.minorSeventh
  )

  val nonChordTones: List[Interval] = List.empty
