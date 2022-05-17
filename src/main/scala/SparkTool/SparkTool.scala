package octo.mentoring.project
package SparkTool

import hash.GeoHash

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf


object SparkTool {
  val udfEncode: UserDefinedFunction  =  udf((lat: Double, lon: Double) => {
    GeoHash.encode(lat: Double, lon: Double)
  })



}
