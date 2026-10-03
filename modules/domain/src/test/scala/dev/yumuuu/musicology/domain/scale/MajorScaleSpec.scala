package dev.yumuuu.musicology.domain.scale

import cats.Eq
import dev.yumuuu.musicology.domain.chord.jazz.Chord
import dev.yumuuu.musicology.domain.chord.jazz.DominantSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MajorSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MajorSixth
import dev.yumuuu.musicology.domain.chord.jazz.MajorTriad
import dev.yumuuu.musicology.domain.chord.jazz.MinorSeventh
import dev.yumuuu.musicology.domain.chord.jazz.MinorSeventhFlatFive
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch
import org.scalatest.flatspec.AnyFlatSpec

final class MajorScaleSpec extends AnyFlatSpec:
  private val chordSetEq = Eq.fromUniversalEquals[Set[Chord]]

  "MajorScale" should "Cメジャーのダイアトニック4和音を返す" in:
    val expected = Set[Chord](
      MajorSeventh(Pitch.c4),
      MinorSeventh(Pitch.d4),
      MinorSeventh(Pitch.e4),
      MajorSeventh(Pitch.f4),
      DominantSeventh(Pitch.g4),
      MinorSeventh(Pitch.a4),
      MinorSeventhFlatFive(Pitch.b4)
    )
    assert(chordSetEq.eqv(MajorScale(Pitch.c4).diatonicChords, expected))

  "MajorScale" should "長調の和声機能に三和音と6thコードも含める" in:
    val scale = MajorScale(Pitch.c4)
    assert(
      chordSetEq.eqv(
        scale.TonicSet,
        Set[Chord](
          MajorTriad(Pitch.c4),
          MajorSixth(Pitch.c4),
          MajorSeventh(Pitch.c4),
          MinorSeventh(Pitch.e4),
          MinorSeventh(Pitch.a4)
        )
      ) &&
        chordSetEq.eqv(
          scale.DominantSet,
          Set[Chord](DominantSeventh(Pitch.g4), MinorSeventhFlatFive(Pitch.b4))
        ) &&
        chordSetEq.eqv(
          scale.SubDominantSet,
          Set[Chord](
            MajorTriad(Pitch.f4),
            MajorSixth(Pitch.f4),
            MajorSeventh(Pitch.f4),
            MinorSeventh(Pitch.d4)
          )
        ) && scale.SubDominantMinorSet.isEmpty
    )

  "MajorScale" should "異なる主音でも各度数から3度ずつ積み重ねた構成音を持つ" in:
    assert(List(Pitch.c4, Pitch.fs4, Pitch.db4, Pitch.c4 + Interval.oneOctaveDown).forall { root =>
      val scale = MajorScale(root)
      val pitches = scale.intervals.map(root + _).toList
      val extended = pitches ++ pitches.map(_ + Interval.oneOctaveUp)
      scale.diatonicChords.toList.sortBy(_.root.midiNoteNumber()).zipWithIndex.forall {
        case (chord, degree) =>
          val expected = extended.slice(degree, degree + 7).zipWithIndex.collect {
            case (pitch, index) if Eq[Int].eqv(index % 2, 0) => pitch
          }
          val actual = chord.chordTones.map(chord.root + _).toList
          Eq.fromUniversalEquals[List[Pitch]].eqv(actual, expected) && chord.nonChordTones.isEmpty
      }
    })
