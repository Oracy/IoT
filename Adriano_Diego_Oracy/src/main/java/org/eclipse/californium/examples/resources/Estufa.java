package org.eclipse.californium.examples.resources;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Estufa {
	
	public static int tempo = 60;
	
	public static double temperatura = 1+Math.round(Math.random()*30);
	
	public static int qtdMinPes = 5;
	
	private static int qtdTotPes = 60;
	
	private static int qtdeAtPes = (int)(1+Math.random()*60);
	
	private static int statusIrrigador = 0; // 0 -> Fechada - 1 -> Aberta
	
	private static int statusEstufa = 0; // 0 -> OFF - 1 -> ON
	
	public static double getTemperatura() {
		return temperatura;
	}
	
	public static void setTempo(int tempo) {
		Estufa.tempo = tempo;
	}
	
	public static int getTempo() {
		return tempo;
	}
	
	public static void setTemperatura(double temp) {
		Estufa.temperatura = temp;
	}

	public static int getQtdeAtPes() {
		return qtdeAtPes;
	}

	public static void setQtdeAtPes(int qtdeAtPes) {
		Estufa.qtdeAtPes = qtdeAtPes;
	}

	public static int getQtdeTotPes() {
		return qtdTotPes;
	}

	public static void setQtdeTotPes(int qtdTotPes) {
		Estufa.qtdTotPes = qtdTotPes;
	}
	
	public static int getQtdeMinPes() {
		return qtdMinPes;
	}

	public static void setQtdeMinPes(int qtdTotPes) {
		Estufa.qtdMinPes = qtdMinPes;
	}
	
	public static int getStatusEstufa() {
		return statusEstufa;
	}

	public static void setStatusEstufa(int statusEstufa) {
		Estufa.statusEstufa = statusEstufa;
	}
	


	
	// Verifica se a porta da Estufa está aberta depois de x minutos (var tempo) e a fecha
	public static void fechaIrrigadorEstufa() {
		Timer cronometro = new Timer();
		TimerTask tarefa = new TimerTask() {
		    @Override
		    public void run() {
		    	if (statusIrrigador == 1) {
		    		statusIrrigador = 0; // Fechada
		    	}
		    }
		};
		cronometro.schedule(tarefa, (tempo*1000), (tempo*1000));
	}
	
	public static void checaQtdePes() {
			Timer cronometro = new Timer();
			TimerTask tarefa = new TimerTask() {
			    @Override
			    public void run() {
			    	if (qtdeAtPes <= qtdMinPes) {
			    		System.out.println("Quantidade mínima de pes atingida.\n Hora de reabastecer a estufa.");
			    	}
			    }
			};
			cronometro.schedule(tarefa, 300000, 300000);
	}
	
	// Verifica se estufa está vazia por mais de 10 minutos, em caso positivo ela é desligada.
	public static void checaStatusEstufa() {
				Timer cronometro = new Timer();
				TimerTask tarefa = new TimerTask() {
				    @Override
				    public void run() {
				    	if (qtdeAtPes == 0) {
				    		System.out.println("Estufa vazia por mais de 10min.\n Desligando...");
				    		try {
								Thread.sleep(6000);
								setStatusEstufa(0);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
				    }
				};
				cronometro.schedule(tarefa, 600000, 600000);
	}
		
}