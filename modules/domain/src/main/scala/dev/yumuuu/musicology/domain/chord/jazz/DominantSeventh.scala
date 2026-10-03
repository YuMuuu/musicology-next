package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** ドミナントセブンスを表す。 */
final case class DominantSeventh(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] = NonEmptyList.of(
    Interval.unis,
    Interval.majorThird,
    Interval.perfectFifth,
    Interval.minorSeventh
  )

  val nonChordTones: List[Interval] = List.empty
