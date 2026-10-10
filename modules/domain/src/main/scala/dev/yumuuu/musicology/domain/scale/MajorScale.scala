package dev.yumuuu.musicology.domain.scale

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.chord.jazz.Chord
import dev.yumuuu.musicology.domain.chord.jazz.DominantSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MajorSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MajorSixth
import dev.yumuuu.musicology.domain.chord.jazz.MajorTriad
import dev.yumuuu.musicology.domain.chord.jazz.MinorSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MinorSeventhFlatFive
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

  val tonic: MajorSeventh = MajorSeventh(root) // ⅠM7
  val supertonic: MinorSeventh = MinorSeventh(root + Interval.majorSecond) // Ⅱm7
  val mediant: MinorSeventh = MinorSeventh(root + Interval.majorThird) // Ⅲm7
  val subdominant: MajorSeventh = MajorSeventh(root + Interval.perfectFourth) // ⅣM7
  val dominant: DominantSeventh = DominantSeventh(root + Interval.perfectFifth) // Ⅴ7
  val submediant: MinorSeventh = MinorSeventh(root + Interval.majorSixth) // Ⅵm7
  val leadingTone: MinorSeventhFlatFive = MinorSeventhFlatFive(
    root + Interval.majorSeventh
  ) // Ⅶm7♭5

  /** 各音を根音に3度ずつ積み重ねた、長音階のダイアトニック4和音。 */
  val diatonicChords: Set[Chord] = Set(
    tonic,
    supertonic,
    mediant,
    subdominant,
    dominant,
    submediant,
    leadingTone
  )

  val TonicSet: Set[Chord] = Set(
    MajorTriad(root), // Ⅰ△
    MajorSixth(root), // Ⅰ6
    tonic,
    mediant,
    submediant
  )

  val SubDominantSet: Set[Chord] = Set(
    MajorTriad(subdominant.root), // Ⅳ△
    MajorSixth(subdominant.root), // Ⅳ6
    subdominant,
    supertonic
  )

  val SubDominantMinorSet: Set[Chord] = Set.empty

  val DominantSet: Set[Chord] = Set(dominant, leadingTone)
