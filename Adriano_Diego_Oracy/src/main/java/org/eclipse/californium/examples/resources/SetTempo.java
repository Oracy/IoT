package org.eclipse.californium.examples.resources;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.BAD_REQUEST;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED;

import java.lang.reflect.Field;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class SetTempo extends CoapResource {

    public SetTempo(String tempo){
        super(tempo);
    }
   
    @Override
    public void handlePUT(CoapExchange exchange) {
    	byte[] payload = exchange.getRequestPayload();
    	
    	String putTempo;
    	int tempo = 0;
        
    	try {
    		putTempo = new String(payload, "UTF-8");	
            
            tempo = Integer.parseInt(putTempo);                      
            
            if(tempo < 30) {
            	exchange.respond(BAD_REQUEST,"Valor de tempo deve ser maior que 30 minutos.\nFavor inserir novamente.");
            }
            else {
            	Estufa estufa = new Estufa();
            	estufa.setTempo(tempo);
            	exchange.respond(CHANGED, "Tempo de fechamento do irrigador alterado para: " + Integer.toString(tempo) + "s.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.respond(BAD_REQUEST, "Temperatura inválida.\nFavor inserir novamente.");
        }
	}
}
