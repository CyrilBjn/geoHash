package octo.mentoring.project
package tree

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

case class hash2TreeConverter(hashList: Array[String]){
  val hashTree: HashTreeNode = hash2TreeConverter.buildHashTree(hashList)

  def udfFindUniquePrefix: UserDefinedFunction  = udf((geoHash: String, hashLength: Int) =>{
    findUniquePrefixGlobal(geoHash, hashLength)
  })

  def findUniquePrefixGlobal(geoHash: String, hashLength: Int): String ={
    if (hashLength >  0)
      findUniquePrefix(geoHash, hashLength - 1)
    else geoHash
  }

  @scala.annotation.tailrec
  private def findUniquePrefix(geoHash: String, currentIndex: Int): String ={
    if (currentIndex==0)
      return geoHash
    hashTree.findByPrefix(geoHash.take(currentIndex-1)).length match {
      case x if x > 1 => geoHash
      case 1 => findUniquePrefix(geoHash.take(currentIndex - 1), currentIndex - 1)
      case _ => ""
    }
  }

  def findUniquePrefixForHash(geoHash: String, hashLength: Int): String = {
    if (hashLength >  0)
      findUniquePrefix(geoHash, hashLength - 1)
    else geoHash
  }
}

object hash2TreeConverter {
  def buildHashTree(hashList: Array[String]): HashTreeNode = {
    val tree = new HashTreeNode()

    for (hash <- hashList){
      tree.append(hash)
    }
    tree
  }
}