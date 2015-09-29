package com.zhenhui.demo.Study.netty.cli;


import com.zhenhui.demo.Study.netty.cli.xt01.MyServer;
import com.zhenhui.demo.Study.netty.core.server.Server;

public class Entry {

    public static void main(String[] args) {

        Server server = new MyServer((short)23456);

        try {
            server.run();

            System.in.read();

            server.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
