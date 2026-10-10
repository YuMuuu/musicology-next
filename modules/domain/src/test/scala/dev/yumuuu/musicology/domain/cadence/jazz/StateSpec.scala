package dev.yumuuu.musicology.domain.cadence.jazz

import cats.Eq
import org.scalatest.flatspec.AnyFlatSpec

final class StateSpec extends AnyFlatSpec:
  private val stateEq = Eq.fromUniversalEquals[State]
  private val resultEq = Eq.fromUniversalEquals[Option[State]]

  private def returnsToTonic(transitions: List[Transition]): Boolean =
    val result = transitions.foldLeft(Option(State.Tonic)) { (state, transition) =>
      state.flatMap(_.transition(transition))
    }
    resultEq.eqv(result, Option(State.Tonic))

  "State" should "各遷移を始点にだけ適用できる" in:
    assert(Transition.values.toList.forall { transition =>
      State.values.toList.forall { state =>
        val expected = Option.when(stateEq.eqv(state, transition.from))(transition.to)
        resultEq.eqv(state.transition(transition), expected)
      }
    })

  "State" should "T->S->D->Tを許容する" in:
    val result = List(
      Transition.TonicToSubDominant,
      Transition.SubDominantToDominant,
      Transition.DominantToTonic
    ).foldLeft(Option(State.Tonic)) { (state, transition) =>
      state.flatMap(_.transition(transition))
    }
    assert(resultEq.eqv(result, Option(State.Tonic)))

  "State" should "T->S->SM->Tを許容する" in:
    val result = List(
      Transition.TonicToSubDominant,
      Transition.SubDominantToSubDominantMinor,
      Transition.SubDominantMinorToTonic
    ).foldLeft(Option(State.Tonic)) { (state, transition) =>
      state.flatMap(_.transition(transition))
    }
    assert(resultEq.eqv(result, Option(State.Tonic)))

  "State" should "T->D->Tを許容する" in:
    assert(
      returnsToTonic(
        List(
          Transition.TonicToDominant,
          Transition.DominantToTonic
        )
      )
    )

  "State" should "T->S->Tを許容する" in:
    assert(
      returnsToTonic(
        List(
          Transition.TonicToSubDominant,
          Transition.SubDominantToTonic
        )
      )
    )

  "State" should "T->SM->Tを許容する" in:
    assert(
      returnsToTonic(
        List(
          Transition.TonicToSubDominantMinor,
          Transition.SubDominantMinorToTonic
        )
      )
    )

  "State" should "T->SM->D->Tを許容する" in:
    assert(
      returnsToTonic(
        List(
          Transition.TonicToSubDominantMinor,
          Transition.SubDominantMinorToDominant,
          Transition.DominantToTonic
        )
      )
    )

  "State" should "T->S->SM->D->Tを許容する" in:
    assert(
      returnsToTonic(
        List(
          Transition.TonicToSubDominant,
          Transition.SubDominantToSubDominantMinor,
          Transition.SubDominantMinorToDominant,
          Transition.DominantToTonic
        )
      )
    )

  "State" should "ブルース進行のDからSを経由してTに戻る" in:
    val result = List(
      Transition.DominantToSubDominant,
      Transition.SubDominantToTonic
    ).foldLeft(Option(State.Dominant)) { (state, transition) =>
      state.flatMap(_.transition(transition))
    }
    assert(resultEq.eqv(result, Option(State.Tonic)))

  "State" should "途中で始点が一致しない遷移があれば失敗する" in:
    val result = List(
      Transition.TonicToDominant,
      Transition.SubDominantToTonic
    ).foldLeft(Option(State.Tonic)) { (state, transition) =>
      state.flatMap(_.transition(transition))
    }
    assert(result.isEmpty)
