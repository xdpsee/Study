package com.zhenhui.demo.Study.sping_data_mongo.dao;

import com.zhenhui.demo.Study.sping_data_mongo.device.EntityRepository;
import com.zhenhui.demo.Study.sping_data_mongo.domain.Device;

public class DeviceRepository implements EntityRepository<Device, String> {

    @Override
    public Device findById(String s) {
        return null;
    }

    @Override
    public boolean insert(Device object) {
        return false;
    }

    @Override
    public boolean update(Device object) {
        return false;
    }

    @Override
    public boolean delete(Device object) {
        return false;
    }
}
