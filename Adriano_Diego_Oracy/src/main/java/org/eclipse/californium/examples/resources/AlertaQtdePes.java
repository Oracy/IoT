package org.eclipse.californium.examples.resources;

import java.util.Random;

public class AlertaQtdePes{

	public AlertaQtdePes() {
				
	}
	
		
	public double Temperature(){
		
		double t=1+Math.round(Math.random()*30);
		
		return t;
	}
	
	public boolean Presence(){
		  Random random = new Random();
		  int tmp = random.nextInt(3) + 1;
		  if (tmp == 1) {	
			  return true;
		}else
			return false;	
	}
}
