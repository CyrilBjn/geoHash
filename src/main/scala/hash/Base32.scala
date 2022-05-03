package octo.mentoring.project
package hash

object Base32 {
  private val BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz"
  private val BITS = Seq(16, 8, 4, 2, 1)

  /**
   * Convertir une liste de booléen en base-32
   * bin : Sequence de booléen Seq(True, False, True False, False)
   * Convert list of boolean bits to a base-32 character. Only the first 5 bits are considere
   *
   */
  def toBase32(bin: Seq[Boolean]): Char = BASE32((BITS zip bin).collect { case (x, true) => x }.sum)

}
