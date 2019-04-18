package com.api.consomeapi;

public class ExecutorChamadas {

	public static void main(String[] args) {
		ConsumidorApi consumidor = ConsumidorApi.getInstance();
		System.out.println(consumidor.doRequest("/cadastros/clientes"));

	}
}
