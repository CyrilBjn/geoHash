package octo.mentoring.project
package SparkTool

import hash.GeoHash

import org.apache.spark.sql.functions.udf


object SparkTool {
  val udfEncode =  udf((lat: Double, lon: Double) => {
    GeoHash.encode(lat: Double, lon: Double)
  })

}
