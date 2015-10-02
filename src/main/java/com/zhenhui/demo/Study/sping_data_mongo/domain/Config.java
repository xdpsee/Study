package com.zhenhui.demo.Study.sping_data_mongo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "configure")
public class Config {

    @Id
    private String id;
    private String IMEI;
    private String name;
    private String comment;

    private Float   timezone;
    private Long    activeDate;
    private Long    expireDate;
    private Boolean locked = false;




}
