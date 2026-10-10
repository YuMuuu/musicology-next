package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** ディミニッシュセブンスを表す。 */
final case class DiminishedSeventh(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] = NonEmptyList.of(
    Interval.unis,
    Interval.minorThird,
    Interval.diminishedFifth,
    Interval.diminishedSeventh
  )

  val nonChordTones: List[Interval] = List.empty
