package dev.yumuuu.musicology.domain.note

import cats.Eq
import org.scalatest.flatspec.AnyFlatSpec

final class PitchSpec extends AnyFlatSpec:
  private val pitchEq = Eq.fromUniversalEquals[Pitch]

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
