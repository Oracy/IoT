package org.eclipse.californium.examples.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.californium.core.coap.CoAP.Type;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.*;


public class AlertPes extends CoapResource {
	public static final int dataCf2 = TEXT_PLAIN;
	public static final int TEXT_PLAIN2 = 0;
	private int dataCf1 = TEXT_PLAIN;
	
	private String timestr;
        
	public AlertPes(String name) {
            super("AlertPes");
	
        setObservable(true);
	    getAttributes().addResourceType("observe");
	    getAttributes().setObservable();
	    setObserveType(Type.CON);

	    Timer timer = new Timer();
	    timer.schedule(new PeriodicTask(), 0, 5000);
        }
        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.setMaxAge(5);
	    exchange.respond(CONTENT, timestr, dataCf1);		
        }
	
    private class PeriodicTask extends TimerTask {
		
		@Override
		public void run() {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date time = new Date();
			timestr=dateFormat.format(time);
			// Call changed to notify subscribers
			changed();
		}
	}
    }