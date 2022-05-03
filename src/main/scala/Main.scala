package octo.mentoring.project

import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    println("Test")

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("Spark Main")
      .config("spark.driver.bindAddress", "127.0.0.1") // to help sparkDriver getting initiated --> to be disabled/or change the localhost address if needed
      .config("spark.driver.host", "127.0.0.1") // to help blockManager through shuffle if needed --> to be disabled/or change the localhost address if needed
      .getOrCreate()

    import spark.implicits._

    val df = spark.read
      .option("header", "true")
      .csv("geoHash/src/main/resources/test_points")

    df.show(false)
    println(df.count()) // 100 000 



  }
}