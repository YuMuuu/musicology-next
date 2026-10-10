package dev.yumuuu.musicology.domain.cadence.jazz

import cats.Eq

enum State:
  case Tonic, Dominant, SubDominant, SubDominantMinor

  /** 現在の状態を始点とする遷移だけを適用する。 */
  def transition(next: Transition): Option[State] =
    Option.when(Eq.fromUniversalEquals[State].eqv(this, next.from))(next.to)
