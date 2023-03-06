package com.freightos.snackvendingmachine.vendingmachine.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.Set;

import static com.freightos.snackvendingmachine.vendingmachine.statemachine.VendingMachineEvent.*;
import static com.freightos.snackvendingmachine.vendingmachine.statemachine.VendingMachineState.*;

@Configuration
@EnableStateMachine
public class VendingMachineConfiguration extends StateMachineConfigurerAdapter<VendingMachineState, VendingMachineEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<VendingMachineState, VendingMachineEvent> states) throws Exception {
        states
                .withStates()
                .initial(IDLE)
                .states(Set.of(VendingMachineState.values()));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<VendingMachineState, VendingMachineEvent> transitions)
            throws Exception {
        transitions
                .withExternal().source(IDLE).target(SELECTING_ITEM).event(SELECT_ITEM)
                .and()
                .withExternal().source(SELECTING_ITEM).target(VendingMachineState.OUT_OF_STOCK).event(VendingMachineEvent.OUT_OF_STOCK)
                .and()
                .withExternal().source(SELECTING_ITEM).target(PROCESSING_PAYMENT).event(PAY_USING_CARD)
                .and()
                .withExternal().source(SELECTING_ITEM).target(COLLECTING_MONEY).event(INSERT_MONEY)
                .and()
                .withExternal().source(COLLECTING_MONEY).target(COLLECTING_MONEY).event(INSERT_MONEY)
                .and()
                .withExternal().source(COLLECTING_MONEY).target(PROCESSING_PAYMENT).guard(paymentGuard())
                .and()
                .withExternal().source(SELECTING_ITEM).target(IDLE).event(CANCEL_TRANSACTION)
                .and()
                .withExternal().source(COLLECTING_MONEY).target(DISPENSING_CHANGE).event(CANCEL_TRANSACTION)
                .and()
                .withExternal().source(PROCESSING_PAYMENT).target(DISPENSING_PRODUCT)
                .and()
                .withExternal().source(SELECTING_ITEM).target(SELECTING_ITEM).event(SELECT_ITEM)
                .and()
                .withExternal().source(DISPENSING_PRODUCT).target(DISPENSING_CHANGE)
                .and()
                .withExternal().source(PROCESSING_PAYMENT).target(DISPENSING_CHANGE).event(NO_CHANGE)
                .and()
                .withExternal().source(VendingMachineState.OUT_OF_STOCK).target(IDLE)
                .and()
                .withExternal().source(DISPENSING_CHANGE).target(IDLE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<VendingMachineState, VendingMachineEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Bean
    public Guard<VendingMachineState, VendingMachineEvent> paymentGuard() {
        return stateContext -> {
            double amount = (Double) stateContext.getExtendedState().getVariables().get("amount");
            double cost = (Double) stateContext.getExtendedState().getVariables().get("cost");
            return amount >= cost;
        };
    }
}
