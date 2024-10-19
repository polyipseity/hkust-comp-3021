package hk.ust.comp3021;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Part1Test {
  private static List<String> expectedOutput;
  private DispatchSystem system;

  @BeforeAll
  public static void setup() throws IOException {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(Part1Test.class.getResourceAsStream("SampleOutputAvailableOrders.txt")))) {
      expectedOutput = reader.lines().toList();
    }
  }

  @BeforeEach
  public void setupEach() throws IOException {
    DispatchSystem.resetDispatchSystem();
    system = DispatchSystem.getInstance();
    system.parseAccounts(getClass().getResourceAsStream("SampleInputAccounts.txt"));
    system.parseDishes(getClass().getResourceAsStream("SampleInputDishes.txt"));
    system.parseOrders(getClass().getResourceAsStream("SampleInputOrders.txt"));
  }

  @Test
  public void testParse() {
    // See `setup`.
  }

  @Test
  public void testOutput() throws IOException {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      system.writeOrders(output, system.getAvailableOrders());
      assertLinesMatch(expectedOutput.stream(), output.toString(StandardCharsets.UTF_8).lines());
    }
  }
}
