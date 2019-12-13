package org.eclipse.californium.examples;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.examples.resources.Estufa;
import org.eclipse.californium.examples.resources.GetQtdePes;
import org.eclipse.californium.examples.resources.GetTemperatura;
import org.eclipse.californium.examples.resources.SetQtdeMinPes;
import org.eclipse.californium.examples.resources.SetTemperatura;
import org.eclipse.californium.examples.resources.SetTempo;
import org.eclipse.californium.examples.resources.LigarEstufa;
import org.eclipse.californium.examples.resources.DesligarEstufa;

import java.lang.reflect.*;


public class GreenHouseServerApp extends CoapServer {

    private static final int COAP_PORT = NetworkConfig.getStandard().getInt(NetworkConfig.Keys.COAP_PORT);
    
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        
        try {

            GreenHouseServerApp server = new GreenHouseServerApp();
    
            server.addEndpoints();
            server.start();
            System.out.println(" ");
            System.out.println("420 Server up");
            System.out.println(" ");
            
        } catch (SocketException e) {
            System.err.println("Falha ao inicializar o server.\nTente de novo: " + e.getMessage());
        }
    }

    private void addEndpoints() {
    	for (InetAddress addr : EndpointManager.getEndpointManager().getNetworkInterfaces()) {
			if (addr instanceof Inet4Address || addr.isLoopbackAddress()) {
				InetSocketAddress bindToAddress = new InetSocketAddress(addr, COAP_PORT);
				addEndpoint(new CoapEndpoint(bindToAddress));
			}
		}
    }

    public GreenHouseServerApp() throws SocketException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
            add(
            new CoapResource("On/Off").add(
            	(new LigarEstufa("On")),
            	(new DesligarEstufa("Off"))
            ),
            new CoapResource("Dados da estufa").add(
        		(new GetQtdePes("Quantidade de pés")),
        		(new GetTemperatura("Temperatura"))
        	),
        	new CoapResource("Configurações").add(
        		(new SetQtdeMinPes("Quantidade mínima de pés")),
        		(new SetTemperatura("Temperatura")),
        		(new SetTempo("Tempo de utilização da irrigação"))
        	));
    }
}
