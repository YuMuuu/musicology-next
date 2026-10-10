package dev.yumuuu.musicology.domain.voicing

import cats.Eq
import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch
import org.scalatest.flatspec.AnyFlatSpec

final class VoicingSpec extends AnyFlatSpec:
  private val pitchEq = Eq.fromUniversalEquals[Pitch]

  "Voicingのfactory" should "音域差が1オクターブ以内ならCloseVoicingを生成する" in:
    val top = Pitch.c4
    val second = Pitch.e4
    val third = Pitch.g4
    val forth = Pitch.c4
    val voicing = Voicing(NonEmptyList.of(top, second, third, forth))

    assert(voicing match
      case Voicing.CloseVoicing(_) =>
          pitchEq.eqv(voicing.pitches.head, top) &&
          pitchEq.eqv(voicing.bottom, forth) &&
          (voicing.innerVoicing.exists(innerVoicing => innerVoicing.pitches.toList match
            case secondPitch :: thirdPitch :: Nil =>
              pitchEq.eqv(secondPitch, second) && pitchEq.eqv(thirdPitch, third)
            case _ => false
          ))
      case Voicing.OpenVoicing(_) => false
    )

  it should "内声がない場合はNoneを返す" in:
    val voicing = Voicing(NonEmptyList.of(Pitch.c4, Pitch.c4))

    assert(voicing.innerVoicing match
      case None => true
      case Some(_) => false
    )

  it should "音域差が1オクターブを超えるならOpenVoicingを生成する" in:
    val voicing = Voicing(
      NonEmptyList.of(
        Pitch.c4 + Interval.oneOctaveUp,
        Pitch.g4,
        Pitch.e4,
        Pitch.c4 + Interval.oneOctaveDown
      )
    )

    assert(voicing match
      case Voicing.CloseVoicing(_) => false
      case Voicing.OpenVoicing(_) => true
    )
