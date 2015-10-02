package com.zhenhui.demo.Study.sping_data_mongo.domain.mobile;

import com.zhenhui.demo.Study.sping_data_mongo.domain.Config;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "configure")
public class MobileConfig extends Config {

    private String platform;
    private String version;

}
