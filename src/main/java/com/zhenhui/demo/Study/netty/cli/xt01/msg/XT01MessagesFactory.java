package com.zhenhui.demo.Study.netty.cli.xt01.msg;

public class XT01MessagesFactory {


    public static XT01Message build(byte protocol, short serail, byte[] content) {

        switch(protocol) {
            case 0x01:
            {
                return XT01SigninMessage.create(serail, content);
            }
            default: {
                return null;
            }
        }
    }


}
