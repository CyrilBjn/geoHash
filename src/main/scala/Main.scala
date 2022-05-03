package octo.mentoring.project


import SparkTool.SparkTool.udfEncode
import org.apache.spark.sql.SparkSession

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

    val geoHashArray = geoHashDf.select("geoHash").map(f=>f.getString(0)).collect
    println(geoHashArray)


  }
}