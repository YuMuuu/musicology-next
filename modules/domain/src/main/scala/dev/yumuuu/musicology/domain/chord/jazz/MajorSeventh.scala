package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** メジャーセブンスを表す。 */
final case class MajorSeventh(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] = NonEmptyList.of(
    Interval.unis,
    Interval.majorThird,
    Interval.perfectFifth,
    Interval.majorSeventh
  )

  val nonChordTones: List[Interval] = List.empty
