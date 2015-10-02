package com.zhenhui.demo.Study.sping_data_mongo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "snapshots")
public class Snapshot {

    @Id
    private String  id;

    private Long    time;
    private Double  latitude;
    private Double  longitude;



}
