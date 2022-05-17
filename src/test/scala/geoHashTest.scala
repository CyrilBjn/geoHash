package octo.mentoring.project

import hash.GeoHash.{Interval, encode, encodeInBit, mixList}
import hash.Base32

import octo.mentoring.project.tree.HashTreeNode
import org.scalatest.funsuite.AnyFunSuite

class geoHashTest extends AnyFunSuite{

  test("Sequence de booléen en base 32"){
    val boolSeq = Seq(true, false, true, true, false)
    val c = Base32.toBase32(boolSeq)
    assert(c == 'q')
  }

  test("Encoder le 34 OCTO"){
    val latitude = 48.868670
    val longitude = 2.333380
    val hashCode = encode(latitude, longitude)
    assert(hashCode == "u09wj070esq5xh")
  }

  test("Encoder en Bit un double"){
    val latitude = 48.868670
    val bits = encodeInBit(latitude, Interval(-90.0, 90.0), 12)
    assert(bits == List(true, true, false, false, false, true, false, true, true, false, false, false))
  }

  test("Mixer 2 listes en alternance"){
    val list1 = List("a","c","e")
    val list2 = List("b","d","f")
    val mixedList = mixList(list1, list2)
    assert(mixedList == List("a", "b", "c", "d", "e", "f"))
  }

  test("Trie Append"){
    val trie = new HashTreeNode()
    trie.append("Maurício")
    println(trie.toString())
    trie.append("San Francisco")
    trie.append("San Diego")
    trie.append("Santo Domingo")
    trie.append("São Paulo")
    println(trie.toString())
  }

}
