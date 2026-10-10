package dev.yumuuu.musicology.domain.chord.jazz

import cats.Eq
import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch
import org.scalatest.flatspec.AnyFlatSpec

final class ChordInversionSpec extends AnyFlatSpec:
  private val resultEq =
    Eq.fromUniversalEquals[Either[ChordInversion.Error, NonEmptyList[Pitch]]]

  "ChordInversion" should "CM7の基本形と各転回形を低音から高音に生成する" in:
    val c5 = Pitch.c4 + Interval.oneOctaveUp
    val e5 = Pitch.e4 + Interval.oneOctaveUp
    val g5 = Pitch.g4 + Interval.oneOctaveUp
    val chord = MajorSeventh(Pitch.c4)
    def matches(inversion: ChordInversion, expected: NonEmptyList[Pitch]): Boolean =
      resultEq.eqv(
        inversion.pitches(chord),
        Right[ChordInversion.Error, NonEmptyList[Pitch]](expected)
      )

    assert(
      matches(
        ChordInversion.RootPosition,
        NonEmptyList.of(Pitch.c4, Pitch.e4, Pitch.g4, Pitch.b4)
      ) &&
        matches(ChordInversion.FirstInversion, NonEmptyList.of(Pitch.e4, Pitch.g4, Pitch.b4, c5)) &&
        matches(ChordInversion.SecondInversion, NonEmptyList.of(Pitch.g4, Pitch.b4, c5, e5)) &&
        matches(ChordInversion.ThirdInversion, NonEmptyList.of(Pitch.b4, c5, e5, g5))
    )

  "ChordInversion" should "非構成和音も含め、最低音より低い音を必要なオクターブ数だけ上げる" in:
    val chord = new Chord:
      val root: Pitch = Pitch.c4
      val chordTones: NonEmptyList[Interval] = MajorTriad(root).chordTones
      val nonChordTones: List[Interval] =
        List(Interval.majorSecond + Interval(0, -2), Interval.majorSixth + Interval.oneOctaveUp)

    assert(
      resultEq.eqv(
        ChordInversion.FirstInversion.pitches(chord),
        Right[ChordInversion.Error, NonEmptyList[Pitch]](
          NonEmptyList.of(
            Pitch.e4,
            Pitch.g4,
            Pitch.c4 + Interval.oneOctaveUp,
            Pitch.d4 + Interval.oneOctaveUp,
            Pitch.a4 + Interval.oneOctaveUp
          )
        )
      )
    )

  "ChordInversion" should "三和音の第3転回形はエラーにする" in:
    assert(
      resultEq.eqv(
        ChordInversion.ThirdInversion.pitches(MajorTriad(Pitch.c4)),
        Left[ChordInversion.Error, NonEmptyList[Pitch]](
          ChordInversion.Error.MissingChordTone(ChordInversion.ThirdInversion)
        )
      )
    )
