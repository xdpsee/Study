package com.zhenhui.demo.Study;


import com.mongodb.MongoClient;
import lombok.Data;
import org.jongo.MongoCursor;

import java.io.Serializable;

public class Jongo {

    public static void main(String[] args) {

        MongoClient database = new MongoClient("127.0.0.1");
        org.jongo.Jongo jongo = new org.jongo.Jongo(database.getDB("com_zhenhui_demo_study"));


        Person person = new Person();
        person.setName("zhenhui chen");

        Address address = new Address();
        address.country = "China";
        address.province = "Henan";
        address.city = "Xiangcheng";
        address.zip = "466200";

        person.setAddress(address);

        jongo.getCollection("persons").insert(person);

        MongoCursor<Person> cursor = jongo.getCollection("persons").find().as(Person.class);
        for (; cursor != null && cursor.hasNext();) {
            Person p = cursor.next();

            System.out.println(p);

        }

        database.close();

    }

}


@Data
class Person implements Serializable {
    String name;
    Address address;
}

@Data
class Address implements Serializable {
    String country;
    String province;
    String city;
    String zip;
}