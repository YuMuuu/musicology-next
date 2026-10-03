package dev.yumuuu.musicology.domain.scale

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** マイナースケール（自然短音階）を表す。 */
final case class MinorScale(root: Pitch) extends Scale:
  val intervals: NonEmptyList[Interval] = NonEmptyList.of(
    Interval.unis,
    Interval.majorSecond,
    Interval.minorThird,
    Interval.perfectFourth,
    Interval.perfectFifth,
    Interval.minorSixth,
    Interval.minorSeventh
  )
