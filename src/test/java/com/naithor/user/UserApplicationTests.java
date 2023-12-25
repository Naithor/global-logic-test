package com.naithor.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class UserApplicationTests {

    @Test
    void mainShouldRunSuccessfully(CapturedOutput output) {
        UserApplication.main(new String[]{});

        assertTrue(output.toString().contains("Started UserApplication"));
    }

}
