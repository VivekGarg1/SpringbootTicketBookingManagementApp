package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.beans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String collectStr= Arrays.stream(args).collect(Collectors.joining(","));
        System.out.println("Command Line Arguments are: "+collectStr);
    }
}
