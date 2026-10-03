package dev.yumuuu.musicology.domain.scale

import cats.data.NonEmptyList
import dev.yumuuu.musicology.domain.note.Interval
import dev.yumuuu.musicology.domain.note.Pitch

/** 基準音と、基準音からの空でない音程列で音階を表す
  */
trait Scale:
  def root: Pitch

  def intervals: NonEmptyList[Interval]
