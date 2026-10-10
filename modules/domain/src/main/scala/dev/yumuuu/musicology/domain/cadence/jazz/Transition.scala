package dev.yumuuu.musicology.domain.cadence.jazz

/** このモデルで扱うカデンツの遷移。 */
enum Transition(val from: State, val to: State):
  case TonicToDominant extends Transition(State.Tonic, State.Dominant)
  case TonicToSubDominant extends Transition(State.Tonic, State.SubDominant)
  case TonicToSubDominantMinor extends Transition(State.Tonic, State.SubDominantMinor)
  case DominantToTonic extends Transition(State.Dominant, State.Tonic)
  case DominantToSubDominant extends Transition(State.Dominant, State.SubDominant) //ブルースで利用される
  case SubDominantToTonic extends Transition(State.SubDominant, State.Tonic)
  case SubDominantToDominant extends Transition(State.SubDominant, State.Dominant)
  case SubDominantToSubDominantMinor extends Transition(State.SubDominant, State.SubDominantMinor)
  case SubDominantMinorToTonic extends Transition(State.SubDominantMinor, State.Tonic)
  case SubDominantMinorToDominant extends Transition(State.SubDominantMinor, State.Dominant)
