package com.zhenhui.demo.Study.sping_data_mongo.device;

import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface EntityRepository<T, ID extends Serializable> extends Repository<T, ID> {

    public T findById(ID id);

    public boolean insert(T object);

    public boolean update(T object);

    public boolean delete(T object);

}
