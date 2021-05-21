package com.fitness.instructorservice.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Document
@Builder(toBuilder = true)
public class BuyingPrograms {
    @Id
    private String id;
    @Indexed
    private String customerId;
    private String name;
    private String imageSrc;
    @Size(max = 1000)
    private String description;
    private String instructorId;
    private String instructorName;
    private List<String> category;
    private double cost;
    private boolean payment;
    private Date date;
}
