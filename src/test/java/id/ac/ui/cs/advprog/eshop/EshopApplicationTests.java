package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void startsApplicationSuccessfully() {
    }

    @Test
    void mainMethodStartsApplicationSuccessfully() {
        try {
            EshopApplication.main(new String[] {});
        } catch (Exception e) {
            fail("Exception thrown while starting application: " + e.getMessage());
        }
    }
}