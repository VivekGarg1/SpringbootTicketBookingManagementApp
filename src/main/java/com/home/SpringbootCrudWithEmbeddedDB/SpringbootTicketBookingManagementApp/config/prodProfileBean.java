package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class prodProfileBean implements  EnvBasedConfig {

    @Override
    public void setUp() {
        System.out.println("Prod Config is setup..");
    }
}
