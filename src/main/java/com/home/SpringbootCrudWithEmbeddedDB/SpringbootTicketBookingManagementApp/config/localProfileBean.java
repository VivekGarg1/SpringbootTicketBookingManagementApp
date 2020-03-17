package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class localProfileBean implements  EnvBasedConfig{
    @Override
    public void setUp() {
        System.out.println("Local Config is setup..");
    }
}
