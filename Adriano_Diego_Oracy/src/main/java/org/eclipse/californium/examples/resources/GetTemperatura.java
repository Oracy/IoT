package org.eclipse.californium.examples.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class GetTemperatura extends CoapResource {
	
    public GetTemperatura(String name) {
            super("Temperatura");	

    }
	
		
    @Override
    public void handleGET(CoapExchange exchange) {
		
		Estufa estufa = new Estufa();
		double t = estufa.getTemperatura();
		
		
        exchange.respond("Temperatura atual da estufa: " + t + "ºC.");
    }
	
}
