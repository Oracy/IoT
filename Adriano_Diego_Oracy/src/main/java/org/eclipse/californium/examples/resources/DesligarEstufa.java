package org.eclipse.californium.examples.resources;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class DesligarEstufa extends CoapResource {

    public DesligarEstufa(String name) {
            super(name);		
    }
	
    @Override
    public void handlePOST(CoapExchange exchange) {
    	
    	String reply="OFF";
        
    	try {
    		Estufa estufa = new Estufa();
    		estufa.setStatusEstufa(0);
            exchange.respond(CHANGED, "Status Estufa: " + reply);
            
        } catch (Exception e) {
            e.printStackTrace();
            exchange.respond(BAD_REQUEST, "Erro no sistema.\nTente novamente.");
        }
	}
	
}
