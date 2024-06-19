package com.hriquelme.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

    @Test
    void testMain() {
        try (MockedStatic<DemoApplication> mocked = Mockito.mockStatic(DemoApplication.class)) {
            String[] args = {};
            DemoApplication.main(args);
            mocked.verify(() -> DemoApplication.main(args));
        }
    }
}