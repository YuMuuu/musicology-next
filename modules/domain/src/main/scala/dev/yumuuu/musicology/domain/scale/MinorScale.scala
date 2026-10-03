package dev.yumuuu.musicology.domain.scale

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.chord.jazz.Chord
import dev.yumuuu.musicology.domain.chord.jazz.DiminishedSeventh
import dev.yumuuu.musicology.domain.chord.jazz.DominantSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MajorSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MajorSixth
import dev.yumuuu.musicology.domain.chord.jazz.MajorTriad
import dev.yumuuu.musicology.domain.chord.jazz.MinorMajorSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MinorSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MinorSeventhFlatFive
import dev.yumuuu.musicology.domain.chord.jazz.MinorSixth
import dev.yumuuu.musicology.domain.chord.jazz.MinorTriad
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** 音程列は自然短音階、和声機能の集合は短調で用いるコードを表す。 */
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

  val TonicSet: Set[Chord] = Set(
    MinorTriad(root), // Ⅰm△
    MinorSixth(root), // Ⅰm6
    MinorSeventh(root), // Ⅰm7
    MinorMajorSeventh(root), // ⅠmM7
    MajorSeventh(root + Interval.minorThird), // ♭ⅢM7
    MinorSeventhFlatFive(root + Interval.majorSixth) // Ⅵm7♭5
  )

  val SubDominantSet: Set[Chord] = Set(
    MajorTriad(root + Interval.perfectFourth), // Ⅳ△
    MajorSixth(root + Interval.perfectFourth), // Ⅳ6
    DominantSeventh(root + Interval.perfectFourth), // Ⅳ7
    MinorSeventh(root + Interval.majorSecond) // Ⅱm7
  )

  val SubDominantMinorSet: Set[Chord] = Set(
    MinorTriad(root + Interval.perfectFourth), // Ⅳm△
    MinorSeventh(root + Interval.perfectFourth), // Ⅳm7
    MinorSixth(root + Interval.perfectFourth), // Ⅳm6
    MinorMajorSeventh(root + Interval.perfectFourth), // ⅣmM7
    MinorSeventhFlatFive(root + Interval.majorSecond), // Ⅱm7♭5
    MajorSeventh(root + Interval.minorSixth), // ♭ⅥM7
    DominantSeventh(root + Interval.minorSeventh) // ♭Ⅶ7
  )

  val leadingToneDiminishedSeventh: DiminishedSeventh = DiminishedSeventh(
    root + Interval.majorSeventh
  ) // Ⅶ○7（減七の和音）

  val DominantSet: Set[Chord] = Set(
    DominantSeventh(root + Interval.perfectFifth), // Ⅴ7
    leadingToneDiminishedSeventh,
    MinorSeventhFlatFive(root + Interval.majorSeventh), // Ⅶm7♭5
    MinorSeventh(root + Interval.perfectFifth) // Ⅴm7
  )
