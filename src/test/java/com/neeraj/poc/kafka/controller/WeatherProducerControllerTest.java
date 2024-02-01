package com.neeraj.poc.kafka.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.support.RequestHandledEvent;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.io.*;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherProducerControllerTest {


    @Autowired
    MockMvc mockMvc;


    @Autowired
    WeatherProducerController weatherController;

    @Test
    void broadcastWeatherIsNotNull() {
        assertNotNull(weatherController);
    }


}