package dev.yumuuu.musicology.domain.chord.jazz

import cats.Eq
import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** コードの最低音にする構成和音を指定する。
  *
  * アヴェイラブルノート、アッパーストラクチャートライアド、ポリコードなどでは、
  * root・3rd・5th・7th を最低音とするこの定義と不整合が生じる場合がある。
  */
enum ChordInversion:
  /** root を最低音とする基本形。 */
  case RootPosition

  /** 3rd を最低音とする第1転回形。 */
  case FirstInversion

  /** 5th を最低音とする第2転回形。 */
  case SecondInversion

  /** 7th を最低音とする第3転回形。 */
  case ThirdInversion

  /** 全構成要素を低音から高音に並べる。最低音より低い音だけをオクターブ上げる。 */
  def pitches(chord: Chord): Either[ChordInversion.Error, NonEmptyList[Pitch]] =
    val steps = this match
      case RootPosition    => 0
      case FirstInversion  => 2
      case SecondInversion => 4
      case ThirdInversion  => 6

    chord.chordTones.find { interval =>
      Eq[Int].eqv(interval.fifths * 4 + interval.octave * 7, steps)
    } match
      case None =>
        Left[ChordInversion.Error, NonEmptyList[Pitch]](ChordInversion.Error.MissingChordTone(this))
      case Some(bassInterval) =>
        val bass = chord.root + bassInterval
        Right[ChordInversion.Error, NonEmptyList[Pitch]](
          chord.chordTones
            .concat(chord.nonChordTones)
            .map { interval =>
              val pitch = chord.root + interval
              val distance = bass.midiNoteNumber() - pitch.midiNoteNumber()
              if distance > 0 then pitch + Interval(0, (distance - 1) / 12 + 1)
              else pitch
            }
            .sortBy(_.midiNoteNumber())
        )

object ChordInversion:
  enum Error:
    case MissingChordTone(inversion: ChordInversion)
