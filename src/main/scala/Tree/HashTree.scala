package octo.mentoring.project
package tree

import scala.collection.JavaConverters.mapAsScalaMapConverter
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object HashTree {
  def apply() : HashTree = new HashTreeNode()
}

sealed trait HashTree extends Traversable[String] {
  def append(key : String): Unit
  def findByPrefix(prefix: String): scala.collection.Seq[String]
}

case class HashTreeNode(val char : Option[Char] = None, var geoHash: Option[String] = None) extends HashTree {

  val leafs: mutable.Map[Char, HashTreeNode] = new java.util.TreeMap[Char, HashTreeNode]().asScala

  def append(key: String): Unit = {

    def appendHelper(node: HashTreeNode, currentIndex: Int): Unit = {
      if (currentIndex == key.length) {
        node.geoHash = Some(key)
      } else {
        val char = key.charAt(currentIndex).toLower
        val result = node.leafs.getOrElseUpdate(char, {
          new HashTreeNode(Some(char))
        })

        appendHelper(result, currentIndex + 1)
      }
    }

    appendHelper(node = this, currentIndex = 0)
  }
  
  override def foreach[U](f: String => U): Unit = {

    def foreachHelper(nodes: HashTreeNode*): Unit = {
      if (nodes.nonEmpty) {
        nodes.foreach(node => node.geoHash.foreach(f))
        foreachHelper(nodes.flatMap(node => node.leafs.values): _*)
      }
    }
    foreachHelper(this)
  }
  
  def findByPrefix(prefix: String): scala.collection.Seq[String] = {

    def helper(currentIndex: Int, node: HashTreeNode, items: ListBuffer[String]): ListBuffer[String] = {
      if (currentIndex == prefix.length) {
        items ++ node
      } else {
        node.leafs.get(prefix.charAt(currentIndex).toLower) match {
          case Some(child) => helper(currentIndex + 1, child, items)
          case None => items
        }
      }
    }

    helper(0, this, new ListBuffer[String]())
  }

}