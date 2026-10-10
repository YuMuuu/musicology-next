package dev.yumuuu.musicology.domain.voicing

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Pitch

/** 和音を構成する音の配置を表す
  *
  * `pitches` は最上声から最下声の順に並べる
  *
  * @param pitches
  *   最上声から最下声の順に並べた、空でない音列
  */
enum Voicing(val pitches: NonEmptyList[Pitch]) derives CanEqual:
  /** 密集配置（close voicing）音域差が1オクターブ以内に収まる音の配置 */
  case CloseVoicing(voicePitches: NonEmptyList[Pitch]) extends Voicing(voicePitches)

  /** 開放配置（open voicing）音域差が1オクターブを超える音の配置 */
  case OpenVoicing(voicePitches: NonEmptyList[Pitch]) extends Voicing(voicePitches)

  // FIXME: メロディの説明が曖昧なので後で確認する
  /** 配置のメロディを構成する最上声を返す */
  def top: Pitch = pitches.head

  /** 配置の最下声を返す */
  def bottom: Pitch = pitches.last

  /** 最上声と最下声を除いた内声を返す */
  def innerVoicing: Option[InnerVoicing] =
    NonEmptyList.fromList(pitches.tail.dropRight(1)).map(InnerVoicing.apply)

object Voicing:
  /** 音域差に応じたvoicingを生成する
    *
    * `pitches` の先頭を最上声、末尾を最下声として、両者の音程が1オクターブ以内なら
    * [[CloseVoicing]]、1オクターブを超えるなら[[OpenVoicing]]を返す
    *
    * @param pitches
    *   最上声から最下声の順に並べた、空でない音列
    */
  def apply(pitches: NonEmptyList[Pitch]): Voicing =
    val interval = pitches.head - pitches.last
    val distance = interval.fifths * 7 + interval.octave * 12
    if distance <= 12 then
      Voicing.CloseVoicing(pitches)
    else Voicing.OpenVoicing(pitches)
