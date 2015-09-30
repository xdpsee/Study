package com.zhenhui.demo.Study.netty.cli.common;

@SuppressWarnings("unused")
public class ByteUtils {

    public static byte[] short2Bytes(short value) {
        byte[] bytes = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (bytes.length - 1 - i) * 8;
            bytes[i] = (byte) ((value >>> offset) & 0xff);
        }

        return bytes;
    }

}
