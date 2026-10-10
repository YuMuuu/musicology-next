package dev.yumuuu.musicology.domain.note

/** SingleInterval（単音程）は1 ~ 7度の範囲の音程を表す DoubleInterval（複音程）は8度以上の音程を表す
  */
enum IntervalSpan:
  case SingleInterval, DoubleInterval

object IntervalSpan:
  def span(interval: Interval): IntervalSpan =
    val diatonicSteps = interval.fifths * 4 + interval.octave * 7
    if diatonicSteps.abs < 7 then IntervalSpan.SingleInterval // 音程が1オクターブ未満
    else IntervalSpan.DoubleInterval // 音程が1オクターブ以上
