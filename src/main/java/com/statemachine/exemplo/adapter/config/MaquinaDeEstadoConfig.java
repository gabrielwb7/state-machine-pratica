package com.statemachine.exemplo.adapter.config;

import com.statemachine.exemplo.domain.enums.EventosDePedido;
import com.statemachine.exemplo.domain.enums.StatusPedidos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class MaquinaDeEstadoConfig extends StateMachineConfigurerAdapter<StatusPedidos, EventosDePedido> {

    @Override
    public void configure (StateMachineConfigurationConfigurer<StatusPedidos,EventosDePedido> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<StatusPedidos, EventosDePedido> states) throws Exception {

        states
                .withStates()
                .initial(StatusPedidos.CRIADO)
                .states(EnumSet.allOf(StatusPedidos.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StatusPedidos, EventosDePedido> transicoes) throws Exception {

        transicoes
                .withExternal()
                    .source(StatusPedidos.CRIADO).target(StatusPedidos.APROVADO).event(EventosDePedido.PAGAMENTO_CONFIRMADO).and()
                .withExternal()
                    .source(StatusPedidos.APROVADO).target(StatusPedidos.NOTA_FISCAL).event(EventosDePedido.NF_EMITIDA).and()
                .withExternal()
                    .source(StatusPedidos.APROVADO).target(StatusPedidos.CANCELADO).event(EventosDePedido.CANCELAR).and()
                .withExternal()
                    .source(StatusPedidos.NOTA_FISCAL).target(StatusPedidos.ENVIADO).event(EventosDePedido.ENVIAR).and()
                .withExternal()
                    .source(StatusPedidos.ENVIADO).target(StatusPedidos.ENTREGUE).event(EventosDePedido.ENTREGAR).and();

    }

    @Bean
    public StateMachineListener<StatusPedidos,EventosDePedido> listener() {
        return new StateMachineListener<StatusPedidos, EventosDePedido>() {
            @Override
            public void stateChanged(State<StatusPedidos, EventosDePedido> de, State<StatusPedidos, EventosDePedido> para) {
                System.out.println("Estado mudou de " + de.getId() + " para " + para.getId());
            }

            @Override
            public void stateEntered(State<StatusPedidos, EventosDePedido> state) {

            }

            @Override
            public void stateExited(State<StatusPedidos, EventosDePedido> state) {

            }

            @Override
            public void eventNotAccepted(Message<EventosDePedido> message) {

            }

            @Override
            public void transition(Transition<StatusPedidos, EventosDePedido> transition) {

            }

            @Override
            public void transitionStarted(Transition<StatusPedidos, EventosDePedido> transition) {

            }

            @Override
            public void transitionEnded(Transition<StatusPedidos, EventosDePedido> transition) {

            }

            @Override
            public void stateMachineStarted(StateMachine<StatusPedidos, EventosDePedido> stateMachine) {

            }

            @Override
            public void stateMachineStopped(StateMachine<StatusPedidos, EventosDePedido> stateMachine) {

            }

            @Override
            public void stateMachineError(StateMachine<StatusPedidos, EventosDePedido> stateMachine, Exception e) {

            }

            @Override
            public void extendedStateChanged(Object o, Object o1) {

            }

            @Override
            public void stateContext(StateContext<StatusPedidos, EventosDePedido> stateContext) {

            }
        };
    }



























}
