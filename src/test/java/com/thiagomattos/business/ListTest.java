package com.thiagomattos.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        
        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10);
        
        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(10, list.size());
        assertEquals(10, list.size());
    }
    
    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        
        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);
        
        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(20, list.size());
    }
    
    @Test
    void testMocckingList_When_GetIsCalled_ShouldReturnThiago() {
        
        // Given / Arrange
        var list = mock(List.class);
        when(list.get(0)).thenReturn("Thiago");
        
        // When / Act & Then / Assert
        assertEquals("Thiago", list.get(0));
        assertNull(list.get(1));
    }
    
    @Test
    void testMocckingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnThiago() {
        
        // Given / Arrange
        var list = mock(List.class);
        
        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        when(list.get(anyInt())).thenReturn("Thiago");
        
        // When / Act & Then / Assert
        assertEquals("Thiago", list.get(anyInt()));
        assertEquals("Thiago", list.get(anyInt()));
    }

}
