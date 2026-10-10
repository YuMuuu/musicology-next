package dev.yumuuu.musicology.domain.voicing

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Pitch

/** 最上声と最下声を除いた、内声の音列を表す
  *
  * @param pitches
  *   上の内声から下の内声の順に並べた音列
  */
final case class InnerVoicing(pitches: NonEmptyList[Pitch])
