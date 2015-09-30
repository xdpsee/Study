package com.zhenhui.demo.Study.netty.cli.xt01.msg;

import lombok.Data;

@Data
public class XT01SigninMessage extends XT01Message {
    private static final int IMEI_LENGTH = 15;

    public short    number; // message serial number
    public String   imei; //IMEI
    public String   appVersion; // application version

    public static XT01SigninMessage create(short serial, byte[] content) {

        assert content.length >= IMEI_LENGTH;

        XT01SigninMessage message = new XT01SigninMessage();
        message.setNumber(serial);
        message.setImei(new String(content, 0, IMEI_LENGTH));
        message.setAppVersion(new String(content, IMEI_LENGTH, content.length - IMEI_LENGTH));

        return null;
    }

    @Override
    public String toString() {
        return "XT01SigninMessage{" +
                "number=" + number +
                ", imei='" + imei + '\'' +
                ", appVersion='" + appVersion + '\'' +
                '}';
    }
}

