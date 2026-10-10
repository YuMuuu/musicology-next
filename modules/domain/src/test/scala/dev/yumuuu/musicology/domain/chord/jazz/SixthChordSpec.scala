package dev.yumuuu.musicology.domain.chord.jazz

import cats.Eq
import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Pitch
import org.scalatest.flatspec.AnyFlatSpec

final class SixthChordSpec extends AnyFlatSpec:
  "MajorSixth" should "長三和音に長6度を加える" in:
    val chord = MajorSixth(Pitch.c4)
    val pitches = chord.chordTones.concat(chord.nonChordTones).map(chord.root + _)
    assert(
      Eq.fromUniversalEquals[NonEmptyList[Pitch]]
        .eqv(
          pitches,
          NonEmptyList.of(Pitch.c4, Pitch.e4, Pitch.g4, Pitch.a4)
        )
    )

  "MinorSixth" should "短三和音に長6度を加える" in:
    val chord = MinorSixth(Pitch.c4)
    val pitches = chord.chordTones.concat(chord.nonChordTones).map(chord.root + _)
    assert(
      Eq.fromUniversalEquals[NonEmptyList[Pitch]]
        .eqv(
          pitches,
          NonEmptyList.of(Pitch.c4, Pitch.eb4, Pitch.g4, Pitch.a4)
        )
    )
