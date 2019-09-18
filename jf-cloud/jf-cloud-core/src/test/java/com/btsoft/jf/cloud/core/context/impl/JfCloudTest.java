package com.btsoft.jf.cloud.core.context.impl;

import com.btsoft.jf.cloud.core.configuration.JfCloudAutoConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest(classes=JfCloudAutoConfiguration.class)
@RunWith(SpringRunner.class)
public class JfCloudTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCurrent(){
    }
}