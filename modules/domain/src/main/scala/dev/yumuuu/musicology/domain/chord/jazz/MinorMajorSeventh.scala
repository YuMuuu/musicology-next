package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** マイナーメジャーセブンスを表す。 */
final case class MinorMajorSeventh(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] = NonEmptyList.of(
    Interval.unis,
    Interval.minorThird,
    Interval.perfectFifth,
    Interval.majorSeventh
  )

  val nonChordTones: List[Interval] = List.empty
