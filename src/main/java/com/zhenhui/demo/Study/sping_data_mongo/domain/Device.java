package com.zhenhui.demo.Study.sping_data_mongo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "devices")
public class Device {
    @Id
    private String id;

    @Indexed
    private String IMEI;
    private String SN;
    private String protocol;

    @Reference
    private Config config;

}
