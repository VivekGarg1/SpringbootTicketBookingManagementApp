package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class devProfileBean implements  EnvBasedConfig {

    @Override
    public void setUp() {
        System.out.println("Dev Config is setup..");
    }
}