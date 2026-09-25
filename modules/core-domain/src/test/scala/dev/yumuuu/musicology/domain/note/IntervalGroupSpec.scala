package dev.yumuuu.musicology.domain.note

import cats.Eq
import cats.kernel.laws.discipline.GroupTests
import org.scalacheck.Arbitrary
import org.scalacheck.Cogen
import org.scalacheck.Gen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.prop.Configuration
import org.typelevel.discipline.scalatest.FlatSpecDiscipline

final class IntervalGroupSpec extends AnyFlatSpec with FlatSpecDiscipline with Configuration:
  given PropertyCheckConfiguration = PropertyCheckConfiguration()

  given Eq[Interval] = Eq.fromUniversalEquals

  given Arbitrary[Interval] = Arbitrary(
    for
      fifths <- Gen.choose(-100, 100)
      octave <- Gen.choose(-100, 100)
    yield Interval(fifths, octave)
  )

  given Cogen[Interval] =
    Cogen[(Int, Int)].contramap(interval => (interval.fifths, interval.octave))

  checkAll("音程の操作は群をなす", GroupTests[Interval].group)(using
    summon[PropertyCheckConfiguration]
  )
