package com.thiagomattos.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;


import java.util.List;

import org.junit.jupiter.api.Test;

public class ListWithBDDTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        
        // Given / Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10);
        
        // When / Act & Then / Assert
        assertThat(list.size(), is (10));
        assertThat(list.size(), is (10));
        assertThat(list.size(), is (10));
    }
    
    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        
        // Given / Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10).willReturn(20);
        
        // When / Act & Then / Assert
        assertThat(list.size(), is (10));
        assertThat(list.size(), is (20));
        assertThat(list.size(), is (20));
    }
    
    @Test
    void testMocckingList_When_GetIsCalled_ShouldReturnThiago() {
        
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(0)).willReturn("Thiago");
        
        // When / Act & Then / Assert
        assertThat(list.get(0), is ("Thiago"));
        assertNull(list.get(1));
    }
    
    @Test
    void testMocckingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnThiago() {
        
        // Given / Arrange
        var list = mock(List.class);
        
        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        given(list.get(anyInt())).willReturn("Thiago");
        
        // When / Act & Then / Assert
        assertThat(list.get(anyInt()), is ("Thiago"));
        assertThat(list.get(anyInt()), is ("Thiago"));
    }
    @Test
    void testMocckingList_When_ThrowsAnException() {
    	
    	// Given / Arrange
    	var list = mock(List.class);
    	
    	// If you are using argument matchers, all arguments
    	// have to be provided by matchers.
    	given(list.get(anyInt())).willThrow(new RuntimeException("Test!"));
    	
    	// When / Act & Then / Assert
    	assertThrows(RuntimeException.class, 
    			() -> { 
    			// When / Act
    				list.get(anyInt());
    			}, 
    			() -> "Should have throw a RuntimeException");
    }

}
