package org.eclipse.californium.examples.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class GetQtdePes extends CoapResource {
	
	double pes;
	int pesEstufa;
	
    public GetQtdePes(String name){
        super(name);
        Estufa estufa = new Estufa();
        pes = Estufa.getQtdeAtPes();
        pesEstufa = Estufa.getQtdeTotPes();
    }
   
    @Override
    public void handleGET(CoapExchange exchange) {
   
    int qtdeRest = pesEstufa - (int)pes;
    
    exchange.respond("Quantidade de pes: " + String.valueOf(pes).split("\\.")[0] + "\n" + 
    				 "Espaço disponível na estufa: " + qtdeRest);
    }
}
