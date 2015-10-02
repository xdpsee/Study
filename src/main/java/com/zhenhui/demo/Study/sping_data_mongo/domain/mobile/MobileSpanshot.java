package com.zhenhui.demo.Study.sping_data_mongo.domain.mobile;

import com.zhenhui.demo.Study.sping_data_mongo.domain.Snapshot;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "snaphsots")
public class MobileSpanshot extends Snapshot {



}
