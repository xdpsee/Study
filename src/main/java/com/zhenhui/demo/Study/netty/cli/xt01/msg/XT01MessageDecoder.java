package com.zhenhui.demo.Study.netty.cli.xt01.msg;

import com.zhenhui.demo.Study.netty.core.codec.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

public class XT01MessageDecoder extends AbstractDecoder<XT01Message> {

    private static final byte[] MAGIC = new byte[] {0x63, 0x63};
    private static final int PROTOCOL_WORD_BYTES = 1;
    private static final int SERIAL_NUMBER_BYTES = 2;
    private static final int PACKAGE_LENGTH_FILED_BYTES = 2;

    private static final int HEADER_SIZE = MAGIC.length + PROTOCOL_WORD_BYTES + PACKAGE_LENGTH_FILED_BYTES;

    @Override
    protected XT01Message decode(ByteBuf buf) {
        final int readableBytes = buf.readableBytes();
        if (readableBytes <= MAGIC.length + PROTOCOL_WORD_BYTES + PACKAGE_LENGTH_FILED_BYTES) {
            return null;
        }

        final int curr = buf.readerIndex();
        final byte[] magic = new byte[MAGIC.length];

        buf.getBytes(curr, magic);
        if (!Arrays.equals(magic, MAGIC)) {
            buf.skipBytes(magic.length);
            return null;
        }

        final byte protocol = buf.getByte(curr + MAGIC.length);
        if (!acceptableProtocol(protocol)) {
            buf.skipBytes(MAGIC.length + PROTOCOL_WORD_BYTES);
            return null;
        }

        final short serial = buf.getShort(curr + MAGIC.length + PROTOCOL_WORD_BYTES);
        final short length = buf.getShort(curr + MAGIC.length + PROTOCOL_WORD_BYTES + SERIAL_NUMBER_BYTES);

        final int need = length - HEADER_SIZE;
        final int remain = readableBytes - HEADER_SIZE;
        if (remain >= need) {
            byte[] content = new byte[length - HEADER_SIZE];
            buf.skipBytes(HEADER_SIZE);
            buf.readBytes(content);
            return XT01MessagesFactory.build(protocol, serial, content);
        }

        return null;
    }

    private boolean acceptableProtocol(byte protocol) {

        if (protocol == 0x01) {
            return true;
        }

        return false;
    }
}



