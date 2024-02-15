package com.goda.ai.model.mongo;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

import java.util.List;

import lombok.Data;

@Document(collection = "datalocation")
@Data
public class Location {
    @Id
    private String id;
    private String text;
    private List<Double> embedding;
    private String name;
    private String address;
    private BusinessHours businessHours;
    private String phoneNumber;
    private Double avgRate;
    private List<Review> reviews;
    private String imageLink;
    private String content;
    private String menu;
    private location location;
    private String type;

    @Data
    public static class BusinessHours {
        private String Wednesday;
        private String Thursday;
        private String Friday;
        private String Saturday;
        private String Sunday;
        private String Monday;
        private String Tuesday;

        // Constructors, Getters, and Setters
    }

    @Data
    public static class Review {
        private String name;
        private String reviewTime;
        private String rating;
        private String reviewContent;

        // Constructors, Getters, and Setters
    }

    @Data
    public static class location {
        private String type;
        private List<Double> coordinates;
    }
}
