package stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {

    TqsStack stack;

    @BeforeEach
    void setUp() {
       stack = new TqsStack();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("A stack is empty on construction.")
    void isEmpty() {
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("A stack has size 0 on construction.")
    void size() {
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x.")
    void pushXpop() {
        stack.push("1");

        assertEquals("1", stack.pop());
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same.")
    void pushXpeek() {
        stack.push("1");
        stack.push("2");

        assertEquals("2", stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n.")
    void afterNpushes() {
        stack.push("1");
        stack.push("2");

        assertEquals(2, stack.size());
        assertEquals(0, stack.isEmpty());
    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    void sizeNpop() {
        afterNpushes();

        stack.pop();
        stack.pop();

        assertEquals(0, stack.size());
        assertEquals(1, stack.isEmpty());
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    void poppingEmpty() {
        assertEquals(1, stack.isEmpty());

        assertThrows(NoSuchElementException.class, () -> { stack.pop(); });
    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException.")
    void peekingEmpty() {
        assertEquals(1, stack.isEmpty());

        assertThrows(NoSuchElementException.class, () -> { stack.peek(); });
    }

    @Test
    @DisplayName("For bounded stacks only, pushing onto a full stack does throw an IllegalStateException")
    void boundedPushing() {
        TqsStack boundedStack = new TqsStack(3);

        boundedStack.push("1");
        boundedStack.push("2");
        boundedStack.push("3");

        assertThrows(IllegalStateException.class, () -> { boundedStack.push("4"); });
    }

}