package dev.yumuuu.musicology.domain.note

import cats.Eq
import org.scalatest.flatspec.AnyFlatSpec

final class PitchSpec extends AnyFlatSpec:
  private val pitchEq = Eq.fromUniversalEquals[Pitch]
  private val intEq = Eq.fromUniversalEquals[Int]

  private val naturals = List(Pitch.c4, Pitch.d4, Pitch.e4, Pitch.f4, Pitch.g4, Pitch.a4, Pitch.b4)
  private val doubleSharps =
    List(Pitch.css4, Pitch.dss4, Pitch.ess4, Pitch.fss4, Pitch.gss4, Pitch.ass4, Pitch.bss4)
  private val doubleFlats =
    List(Pitch.cbb4, Pitch.dbb4, Pitch.ebb4, Pitch.fbb4, Pitch.gbb4, Pitch.abb4, Pitch.bbb4)
  private val pitchesEq = Eq.fromUniversalEquals[List[Pitch]]

  "Pitch.doubleSharp()" should "全7音のダブルシャープを生成する" in:
    assert(pitchesEq.eqv(naturals.map(_.doubleSharp()), doubleSharps))

  "Pitch.doubleFlat()" should "全7音のダブルフラットを生成する" in:
    assert(pitchesEq.eqv(naturals.map(_.doubleFlat()), doubleFlats))

  "Pitchのダブル変化記号" should "MIDI音高を2半音変化させる" in:
    assert(naturals.forall { natural =>
      intEq.eqv(natural.doubleSharp().midiNoteNumber(), natural.midiNoteNumber() + 2) &&
      intEq.eqv(natural.doubleFlat().midiNoteNumber(), natural.midiNoteNumber() - 2)
    })

  "Pitchのダブル変化記号" should "オクターブ移動後も適用でき、逆操作で元の音に戻る" in:
    assert(naturals.forall { natural =>
      val shifted = natural + Interval.oneOctaveUp
      pitchEq.eqv(shifted.doubleSharp(), natural.doubleSharp() + Interval.oneOctaveUp) &&
      pitchEq.eqv(shifted.doubleFlat(), natural.doubleFlat() + Interval.oneOctaveUp) &&
      pitchEq.eqv(shifted.doubleSharp().doubleFlat(), shifted) &&
      pitchEq.eqv(shifted.doubleFlat().doubleSharp(), shifted)
    })

  "Pitch.a4.flat()" should "Pitch.ab4と一致する" in:
    assert(pitchEq.eqv(Pitch.a4.flat(), Pitch.ab4))

  "Pitch.a4.sharp()" should "Pitch.as4と一致する" in:
    assert(pitchEq.eqv(Pitch.a4.sharp(), Pitch.as4))

  "Pitch.b4.flat()" should "Pitch.bb4と一致する" in:
    assert(pitchEq.eqv(Pitch.b4.flat(), Pitch.bb4))

  "Pitch.b4.flat().flat()" should "Pitch.bbb4と一致する" in:
    assert(pitchEq.eqv(Pitch.b4.flat().flat(), Pitch.bbb4))

  "Pitch.b4.sharp()" should "Pitch.bs4と一致する" in:
    assert(pitchEq.eqv(Pitch.b4.sharp(), Pitch.bs4))
