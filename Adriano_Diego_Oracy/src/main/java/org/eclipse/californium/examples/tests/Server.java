/*******************************************************************************
 * Copyright (c) 2015 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 ******************************************************************************/
package org.eclipse.californium.examples.tests;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http2.Http2Connection.Listener;
import io.netty.handler.codec.http2.Http2Stream;

import java.util.Map;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;

import static org.eclipse.californium.core.coap.MediaTypeRegistry.APPLICATION_JSON;

import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.server.resources.CoapExchange;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;

import java.util.Collection;
import java.util.HashMap;

public class Server extends CoapServer {

    private static final int INITIAL_PORT = 64000;
    private static final int NUMBER_OF_SENSORS = 2;
    private final Map<Integer, TemperatureSensor> sensors = new HashMap<>();

    public Server() {
        super();
        
        TemperatureSensorResource resource = new TemperatureSensorResource(sensors);
        
        for (int i = 0; i < NUMBER_OF_SENSORS; i++) {
            final int port = INITIAL_PORT + i;
            addEndpoint(new CoapEndpoint(port));
            final TemperatureSensor sensor = new TemperatureSensor(port);
            sensor.setListener(resource);
            sensors.put(port, sensor);
        }

        add(resource);
    }

    public static void main(String[] args) {
        Server server = new Server();

        server.start();
    }

    @Override
    public void start() {
        final Collection<TemperatureSensor> values = sensors.values();
        for(TemperatureSensor sensor : values) {
            new Thread(sensor).start();
        }
        
        super.start();
    }

}