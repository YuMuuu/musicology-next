package dev.yumuuu.musicology.domain.scale

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** メジャースケール（長音階）を表す。 */
final case class MajorScale(root: Pitch) extends Scale:
  val intervals: NonEmptyList[Interval] = NonEmptyList.of(
    Interval.unis,
    Interval.majorSecond,
    Interval.majorThird,
    Interval.perfectFourth,
    Interval.perfectFifth,
    Interval.majorSixth,
    Interval.majorSeventh
  )
