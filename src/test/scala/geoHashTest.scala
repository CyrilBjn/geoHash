package octo.mentoring.project

import org.scalatest.funsuite.AnyFunSuite
import hash.Hash._

class geoHashTest extends AnyFunSuite{

  test("Sequence de booléen en base 32"){
    assert('q' == 'q')
  }

  test("Byte transformation of double"){
    val latitude = 48.868670
    val bits = toBits(latitude, Interval(-90.0, 90.0), 12)
    println(bits)
    assert(bits == List(true, true, false, false, false, true, false, true, true, false, false, false))
  }

  test("Association of the coordinate Byte lists"){
    val lat_bits = List(true, true, true, true, true, true, true, true, true, true, true, true)
    val lon_bits = List(false, false, false, false, false, false, false, false, false, false, false, false)
    val mixedList = mixList(lat_bits, lon_bits)
    assert(mixedList == List(true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false))
  }

  test("Boolean list to b32"){
    val bool_list = List(true, false, true, true, false)
    val value = tobase32(bool_list)
    assert(value == 'q')
  }

  test("Hash and binary equivalent are equals"){

  }
}
