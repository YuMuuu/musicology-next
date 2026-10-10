package dev.yumuuu.musicology.domain.scale

import cats.Eq
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
import dev.yumuuu.musicology.domain.note.Pitch
import dev.yumuuu.musicology.domain.note.Interval
import org.scalatest.flatspec.AnyFlatSpec

final class MinorScaleSpec extends AnyFlatSpec:
  private val chordSetEq = Eq.fromUniversalEquals[Set[Chord]]

  "MinorScale" should "Cマイナーのトニックを構成するコードを返す" in:
    assert(
      chordSetEq.eqv(
        MinorScale(Pitch.c4).TonicSet,
        Set[Chord](
          MinorTriad(Pitch.c4),
          MinorSixth(Pitch.c4),
          MinorSeventh(Pitch.c4),
          MinorMajorSeventh(Pitch.c4),
          MajorSeventh(Pitch.eb4),
          MinorSeventhFlatFive(Pitch.a4)
        )
      )
    )

  "MinorScale" should "Cマイナーのサブドミナントを構成するコードを返す" in:
    assert(
      chordSetEq.eqv(
        MinorScale(Pitch.c4).SubDominantSet,
        Set[Chord](
          MajorTriad(Pitch.f4),
          MajorSixth(Pitch.f4),
          DominantSeventh(Pitch.f4),
          MinorSeventh(Pitch.d4)
        )
      )
    )

  "MinorScale" should "Cマイナーのサブドミナントマイナーを構成するコードを返す" in:
    assert(
      chordSetEq.eqv(
        MinorScale(Pitch.c4).SubDominantMinorSet,
        Set[Chord](
          MinorTriad(Pitch.f4),
          MinorSeventh(Pitch.f4),
          MinorSixth(Pitch.f4),
          MinorMajorSeventh(Pitch.f4),
          MinorSeventhFlatFive(Pitch.d4),
          MajorSeventh(Pitch.ab4),
          DominantSeventh(Pitch.bb4)
        )
      )
    )

  "MinorScale" should "Ⅶ○7をB・D・F・A♭の減七の和音として生成する" in:
    val chord = MinorScale(Pitch.c4).leadingToneDiminishedSeventh
    val pitches = chord.chordTones.map(chord.root + _)
    assert(
      Eq.fromUniversalEquals[NonEmptyList[Pitch]]
        .eqv(
          pitches,
          NonEmptyList.of(
            Pitch.b4,
            Pitch.d4 + Interval.oneOctaveUp,
            Pitch.f4 + Interval.oneOctaveUp,
            Pitch.ab4 + Interval.oneOctaveUp
          )
        )
    )

  "MinorScale" should "Cマイナーのドミナントを構成するコードを返す" in:
    assert(
      chordSetEq.eqv(
        MinorScale(Pitch.c4).DominantSet,
        Set[Chord](
          DominantSeventh(Pitch.g4),
          DiminishedSeventh(Pitch.b4),
          MinorSeventhFlatFive(Pitch.b4),
          MinorSeventh(Pitch.g4)
        )
      )
    )
