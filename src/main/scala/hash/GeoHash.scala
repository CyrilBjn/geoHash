package octo.mentoring.project
package hash

import Base32.toBase32

object GeoHash {

  case class Interval(min: Double, max: Double)
  private val latRange: Interval = Interval(-90.0, 90.0)
  private val longRang: Interval = Interval(-180.0, 180.0)

  /**
   * Encode lat/long as a base32.
   * Precision = 12 (Number of char)
   */
  def encode(lat: Double, long: Double): String = { // scalastyle:ignore
    val numbits = (12 * 5) / 2
    val latBits = encodeInBit(lat, latRange, numbits)
    val lonBits = encodeInBit(long, longRang, numbits + 6)
    val bits = mixList(lonBits, latBits)
    val grouped = bits.grouped(5)
    grouped.map(toBase32).mkString // scalastyle:ignore
  }

  def mixList[A](a: List[A], b: List[A]): List[A] = a match {
    case h :: t => h :: mixList(b, t)
    case _      => b
  }

  def encodeInBit(input: Double, inter: Interval, p: Int): List[Boolean] = {
    if (p == 0) Nil
    else {
      val mid = getMid(inter)
      if (input >= mid) {
        true :: encodeInBit(input, Interval(mid, inter.max), p - 1)
      }
      else {
        false :: encodeInBit(input, Interval(inter.min, mid), p - 1)
      }
    }
  }

  private def getMid(b: Interval): Double = (b.min + b.max) / 2.0


}
