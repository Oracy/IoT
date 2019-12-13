package org.eclipse.californium.examples.resources;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.BAD_REQUEST;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED;


import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class SetQtdeMinPes extends CoapResource {

    public SetQtdeMinPes(String pes){
        super(pes);
    }
   
    @Override
    public void handlePUT(CoapExchange exchange) {
    	byte[] payload = exchange.getRequestPayload();
    	
    	String putPes;
    	int pes = 0;
        
    	try {
    		putPes = new String(payload, "UTF-8");	
           
            pes = Integer.parseInt(putPes);
            
            if(pes < 1 || pes > 60) {
            	exchange.respond(BAD_REQUEST,"A quantidade de pés deve estar entre 1 e 60.\n Favor inserir novamente.");
            	
            }
            else {
            	Estufa estufa = new Estufa();
            	estufa.setQtdeMinPes(pes);
            	exchange.respond(CHANGED,"Quantidade mínima de pes para alerta alterada para: " + Integer.toString(pes) + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.respond(BAD_REQUEST, "Valor inválido.\nFavor inserir novamente.");
            
        }
	}
}
