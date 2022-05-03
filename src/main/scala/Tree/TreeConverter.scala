package octo.mentoring.project
package Tree

object TreeConverter {
  def convertInTree(hashList: Array[String]): Tree = {
    var tree = new Tree()
    hashList.foreach{tree.append(_)}
    tree
  }

}
