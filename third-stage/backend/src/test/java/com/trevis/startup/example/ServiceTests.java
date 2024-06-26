package com.trevis.startup.example;

// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// import com.trevis.startup.example.entities.ServiceEntity;
import com.trevis.startup.example.interfaces.ServiceEntityService;


@SpringBootTest
public class ServiceTests {

    @Autowired
    ServiceEntityService serviceRepo;

    @Test
    void getTest() {
        // List<ServiceEntity> serv = serviceRepo.get("A", 1, 5);
        // assertNotEquals(serv, null);
        // assertTrue(serv.size() >= 0);
        // assertTrue(serv.size() <= 5);
    }
}