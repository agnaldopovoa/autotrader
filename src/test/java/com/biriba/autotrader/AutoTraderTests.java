package com.biriba.autotrader;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutoTraderTests {

	@Test
	void contextLoads() {
		assertArrayEquals(Arrays.asList("one").toArray(), Arrays.asList("one").toArray());
	}

}
