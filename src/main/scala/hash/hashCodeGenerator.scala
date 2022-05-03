package hash

import scala.collection.mutable.ListBuffer

object Hash {

    case class Interval (min: Double, max: Double)
    def toBits(coord: Double, inter: Interval, prec: Int): List[Boolean] = {
        var bits = new ListBuffer[Boolean]()
        var min = inter.min
        var max = inter.max
        for ( i <- 1 to prec){
            var mid = (max + min) / 2
            if (coord > mid) {
                bits += true
                min = mid
            } else {
                bits += false
                max = mid
            }
        }
        bits.toList
    }

    def mixList(lat_list: List[Boolean], lon_list: List[Boolean]): List[Boolean] = {
        var mixedList = new ListBuffer[Boolean]()
        for ( i <- 0 to lat_list.size - 1) {
            mixedList += lat_list(i)
            mixedList += lon_list(i)
        }
        mixedList.toList
    }
    val BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz"
    val BITS = Seq(16, 8, 4, 2, 1)
    def tobase32(bool_list: List[Boolean]): Char = {
        val zipBool = BITS zip bool_list
        val collectedList = zipBool.collect{case(x,true) => x}
        val somme = collectedList.sum // 22
        BASE32(somme)
    }
}
