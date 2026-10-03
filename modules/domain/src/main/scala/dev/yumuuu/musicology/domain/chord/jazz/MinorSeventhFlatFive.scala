package dev.yumuuu.musicology.domain.chord.jazz

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** マイナーセブンスフラットファイブを表す。 */
final case class MinorSeventhFlatFive(root: Pitch) extends Chord:
  val chordTones: NonEmptyList[Interval] = NonEmptyList.of(
    Interval.unis,
    Interval.minorThird,
    Interval.diminishedFifth,
    Interval.minorSeventh
  )

  val nonChordTones: List[Interval] = List.empty
