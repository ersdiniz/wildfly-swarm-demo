package com.diniz;

import org.wildfly.swarm.Swarm;

public class Main {

    public static void main(String... args) throws Exception {
        final Swarm swarm = new Swarm(args);
        swarm.start();
        swarm.deploy();
    }
}