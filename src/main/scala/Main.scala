package octo.mentoring.project


import SparkTool.SparkTool.udfEncode

import octo.mentoring.project.tree.hash2TreeConverter
import octo.mentoring.project.tree.hash2TreeConverter.buildHashTree
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.lit

object Main {
  def main(args: Array[String]): Unit = {
    //val filename = args(0)
    val spark = SparkSession.builder()
      .master("local")
      .appName("SparkByExample")
      .getOrCreate()
    import spark.implicits._
    val df = spark.read
      .option("header", "true")
      .csv("/Users/cyril.brajon/IdeaProjects/geoHash/src/main/resources/test_points")
    val geoHashDf = df
      .withColumn("geoHash", udfEncode($"lat",$"lng"))

    val geoHashList = geoHashDf.select("geoHash").map(f=>f.getString(0)).collect
    val tree = hash2TreeConverter(geoHashList)
    val resultData = geoHashDf.withColumn("unique_prefix", tree.udfFindUniquePrefix($"geohash", lit(12)))

    resultData.show(false)
    println(resultData.count())
    println(resultData.dropDuplicates("unique_prefix").count())
    println(resultData.dropDuplicates("geoHash").count())

  }
}