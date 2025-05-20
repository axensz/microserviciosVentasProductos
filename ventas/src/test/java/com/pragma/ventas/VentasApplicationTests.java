package com.pragma.ventas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class VentasApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> {
			VentasApplication.main(new String[]{"--server.port=0"});
		});
	}

	@Test
	void testConstructor() {
		VentasApplication app = new VentasApplication();
		assertNotNull(app);
	}

	@Test
	void testMainWithArgs() {
		assertDoesNotThrow(() -> {
			VentasApplication.main(new String[]{"--spring.profiles.active=test", "--server.port=0"});
		});
	}

}
