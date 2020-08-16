package com.github.panhongan.spring.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author panhongan
 * @since 2020.8.16
 * @version 1.0
 */

@RunWith(MockitoJUnitRunner.class)
public abstract class SpringTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.inject();
    }

    protected abstract void inject();
}
