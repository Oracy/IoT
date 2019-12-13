package org.eclipse.californium.examples.resources;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.BAD_REQUEST;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED;

import java.lang.reflect.Field;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class SetTemperatura extends CoapResource {

    public SetTemperatura(String temperatura){
        super(temperatura);
    }
   
    @Override
    public void handlePUT(CoapExchange exchange) {
    	byte[] payload = exchange.getRequestPayload();
    	
    	String putTemp;
    	double temp = 0;
        
    	try {
    		putTemp = new String(payload, "UTF-8");	
            
            temp = Integer.parseInt(putTemp);
            
            if(temp < 28 || temp > 30 ) {
            	exchange.respond("A temperatura da Estufa deve estar entre 28ºC e 30ºC.\n Favor inserir novamente.");
            }
            else {
            	Estufa estufa = new Estufa ();
            	estufa.setTemperatura(temp);
                exchange.respond(CHANGED, "Temperatura alterada para: " + Double.toString(temp) + "ºC.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.respond(BAD_REQUEST, "Temperatura inválida.\nFavor inserir novamente.");
        }
	}
}
