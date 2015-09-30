package com.zhenhui.demo.Study.netty.cli.xt01.msg.out;

import com.zhenhui.demo.Study.netty.cli.common.ByteUtils;
import com.zhenhui.demo.Study.netty.core.message.Response;

public class XT01SigninResponse extends Response {

    public XT01SigninResponse(String imei) {

        assert  imei != null && imei.length() == 15;

        stream.write(new byte[]{0x63, 0x63}); // magic
        stream.write(new byte[]{0x01}); // protocol
        stream.write(new byte[]{0x01}); // serial number

        short length = (short)(2 + 3 + imei.length());
        stream.write(ByteUtils.short2Bytes(length)); // package length
        stream.write(imei.getBytes());
    }

}
