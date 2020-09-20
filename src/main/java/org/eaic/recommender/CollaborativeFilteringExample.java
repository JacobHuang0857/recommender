package org.eaic.recommender;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.mllib.linalg.distributed.CoordinateMatrix;
import org.apache.spark.mllib.linalg.distributed.MatrixEntry;
import org.apache.spark.sql.*;

import java.io.Serializable;

import static org.apache.spark.sql.functions.*;
import static org.apache.spark.sql.functions.lit;

public class CollaborativeFilteringExample {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("Recommender")
                .config(new SparkConf())
                .master("local")
                .getOrCreate();

        spark.sparkContext()
                .hadoopConfiguration()
                .set("google.cloud.auth.service.account.json.keyfile",
                        "/Users/huangchangmai/Downloads/datalake-8660abe96d5e.json");

        Dataset<Row> ratings = spark
                .read()
                .option("inferSchema", true)
                .option("header", true)
                .csv("ratings_original.csv");
//                .toDF("userId", "movieId", "rating", "timestamp");
        ratings.show();
//        JavaRDD<Rating> ratingsRDD = spark
//                .read().textFile("data/ratings_original.csv").javaRDD()
//                .map(Rating::parseRating);

//        Encoder<Rating> ratingEncoder = Encoders.bean(Rating.class);
//        Dataset<Row> ratings = spark.createDataFrame(ratingsRDD, Rating.class);
        // Calculate users' ratings means

//        ratings.withColumn("avg(rating)", avg(col("rating")));
        Dataset<Row> userMean =  ratings.groupBy("userId").agg(avg("rating"));
//                .agg(avg(ratings.col("rating")));
        userMean.show();

        Dataset<Row> normalized = ratings
//                .join(userMean, ratings.col("userId")
//                .equalTo(userMean.col("userId")))
                .withColumn("Normalized", abs(coalesce(col("rating"), lit(0)).minus(coalesce(col("avg(rating)"), lit(0)))))
                .withColumn("normalized_rating", col("Normalized").divide(countDistinct(col("userId"))))
                .withColumn("predict_rating", col("rating").minus("normalized_rating"));
        normalized.show();
//        List<Row> data = Arrays.asList(
//                RowFactory.create(Vectors.dense(2.0, 3.0, 5.0), 1.0),
//                RowFactory.create(Vectors.dense(4.0, 6.0, 7.0), 2.0)
//        );
//
//        StructType schema = new StructType(new StructField[]{
//                new StructField("features", new VectorUDT(), false, Metadata.empty()),
//                new StructField("weight", DataTypes.DoubleType, false, Metadata.empty())
//        });
//
//        Dataset<Row> df = spark.createDataFrame(data, schema);
//
//        Row result1 = df.select(Summarizer.metrics("mean", "variance")
//                .summary(new Column("features"), new Column("weight")).as("summary"))
//                .select("summary.mean", "summary.variance").first();
//        System.out.println("with weight: mean = " + result1.<Vector>getAs(0).toString() +
//                ", variance = " + result1.<Vector>getAs(1).toString());
//
//        Row result2 = df.select(
//                Summarizer.mean(new Column("features")),
//                Summarizer.variance(new Column("features"))
//        ).first();
//        System.out.println("without weight: mean = " + result2.<Vector>getAs(0).toString() +
//                ", variance = " + result2.<Vector>getAs(1).toString());

//        *-------------------------------------*
//        Encoder<MatrixEntry> matrixEntryEncoder = Encoders.bean(MatrixEntry.class);
//        JavaRDD<MatrixEntry> r = normalized.map((MapFunction<Row, MatrixEntry>) row -> new MatrixEntry(Integer.valueOf(row.getInt(0)), Integer.valueOf(row.getInt(1)), Double.valueOf(row.getDouble(6))), matrixEntryEncoder).javaRDD();
//        CoordinateMatrix coordinateMatrix = new CoordinateMatrix(r.rdd());
//
//        coordinateMatrix.entries().saveAsObjectFile("matrix.txt");
//        *--------------------------------------*


//        Dataset<Row>[] splits = ratings.randomSplit(new double[]{0.8, 0.2});
//        Dataset<Row> training = splits[0];
//        Dataset<Row> test = splits[1];

// Build the recommendation model using ALS on the training data
//        ALS als = new ALS()
//                .setMaxIter(5)
//                .setRegParam(0.01)
//                .setUserCol("userId")
//                .setItemCol("movieId")
//                .setRatingCol("rating");
//        ALSModel model = als.fit(training);

// Evaluate the model by computing the RMSE on the test data
// Note we set cold start strategy to 'drop' to ensure we don't get NaN evaluation metrics
//        model.setColdStartStrategy("drop");
//        Dataset<Row> predictions = model.transform(test);
//
//        RegressionEvaluator evaluator = new RegressionEvaluator()
//                .setMetricName("rmse")
//                .setLabelCol("rating")
//                .setPredictionCol("prediction");
//        Double rmse = evaluator.evaluate(predictions);
//        System.out.println("Root-mean-square error = " + rmse);

// Generate top 10 movie recommendations for each user
//        Dataset<Row> userRecs = model.recommendForAllUsers(10);
// Generate top 10 user recommendations for each movie
//        Dataset<Row> movieRecs = model.recommendForAllItems(10);

// Generate top 10 movie recommendations for a specified set of users
//        Dataset<Row> users = ratings.select(als.getUserCol()).distinct().limit(3);
//        Dataset<Row> userSubsetRecs = model.recommendForUserSubset(users, 10);
// Generate top 10 user recommendations for a specified set of movies
//        Dataset<Row> movies = ratings.select(als.getItemCol()).distinct().limit(3);
//        Dataset<Row> movieSubSetRecs = model.recommendForItemSubset(movies, 10);

    }

    public static class Rating implements Serializable {
        private int userId;
        private int movieId;
        private double rating;
        private long timestamp;

        public Rating() {}

        public Rating(int userId, int movieId, double rating, long timestamp) {
            this.userId = userId;
            this.movieId = movieId;
            this.rating = rating;
            this.timestamp = timestamp;
        }

        public int getUserId() {
            return userId;
        }

        public int getMovieId() {
            return movieId;
        }

        public double getRating() {
            return rating;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public static Rating parseRating(String str) {
            String[] fields = str.split(",");
            if (fields.length != 4) {
                throw new IllegalArgumentException("Each line must contain 4 fields");
            }
            int userId = Integer.parseInt(fields[0]);
            int movieId = Integer.parseInt(fields[1]);
            double rating = Double.parseDouble(fields[2]);
            long timestamp = Long.parseLong(fields[3]);
            return new Rating(userId, movieId, rating, timestamp);
        }
    }
}
