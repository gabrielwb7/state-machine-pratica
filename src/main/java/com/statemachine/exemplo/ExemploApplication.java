package com.statemachine.exemplo;

import com.statemachine.exemplo.domain.enums.EventosDePedido;
import com.statemachine.exemplo.domain.enums.StatusPedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class ExemploApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<StatusPedidos, EventosDePedido> maquinaDeEstado;

	public static void main(String[] args) {
		SpringApplication.run(ExemploApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Iniciando máquina de estado");
		maquinaDeEstado.sendEvent(EventosDePedido.PAGAMENTO_CONFIRMADO);
		System.out.println("****************");
		maquinaDeEstado.sendEvent(EventosDePedido.NF_EMITIDA);
		System.out.println("****************");
		maquinaDeEstado.sendEvent(EventosDePedido.ENVIAR);
		System.out.println("****************");
		maquinaDeEstado.sendEvent(EventosDePedido.ENTREGAR);
		System.out.println("Finalizando...");

	}
}
