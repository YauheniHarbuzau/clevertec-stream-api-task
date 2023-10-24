package by.clevertec;

import by.clevertec.model.Animal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static by.clevertec.Main.task1;
import static by.clevertec.Main.task14;
import static by.clevertec.Main.task4;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Nested
    class Task1Test {
        @Test
        void checkTask1ShouldReturnCorrectListSize() {
            assertEquals(7, task1().size());
        }

        @Test
        void checkTask1ShouldReturnCorrectListValue() {
            List<Animal> actualList = task1();
            assertAll(
                    () -> assertEquals("Snake, buttermilk", actualList.get(0).getBread()),
                    () -> assertEquals("Jackal, silver-backed", actualList.get(6).getBread())
            );
        }

        @Test
        void checkTask1ShouldReturnCorrectPrintStream() {
            task1();
            assertAll(
                    () -> assertTrue(outputStreamCaptor.toString().contains("Snake, buttermilk")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("European stork")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("Flamingo, chilean")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("Red-breasted cockatoo")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("Blue-tongued lizard")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("Wolf spider")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("Jackal, silver-backed"))
            );
        }
    }

    @Nested
    class Task4Test {
        @Test
        void checkTask4ShouldReturnCorrectResult() {
            assertEquals(476L, task4());
        }

        @Test
        void checkTask4ShouldReturnCorrectPrintStream() {
            task4();
            assertEquals("476", outputStreamCaptor.toString().trim());
        }
    }

    @Nested
    class Task14Test {
        @Test
        void checkTask14ShouldReturnCorrectResult() {
            BigDecimal expected = new BigDecimal("29555403.43");
            assertEquals(expected, task14());
        }

        @Test
        void checkTask14ShouldReturnCorrectPrintStream() {
            task14();
            assertAll(
                    () -> assertTrue(outputStreamCaptor.toString().contains("1) 396862,08$")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("2) 8724624,76$")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("3) 2485936,36$")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("4) 572626,35$")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("5) 17220070,75$")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("6) 155283,12$")),
                    () -> assertTrue(outputStreamCaptor.toString().contains("Total Revenue: 29555403,43$"))
            );
        }
    }
}
