package com.thiagomattos.business;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.thiagomattos.service.CourseService;

class CourseBusinessMockWithBDDTest {

    CourseService mockService;
    CourseBusiness business;
    List<String> courses;
    
    @BeforeEach
    void setup() {
        
        // Given / Arrange
        mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);
        
        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
            );
    }
    
    @Test
    void testCoursesRelatedToSpring_When_UsingAMock() {
        
        // Given / Arrange
        when(mockService.retrieveCourses("Lucas"))
            .thenReturn(courses);
            
        // When / Act
        var filteredCourses =
            business.retriveCoursesRelatedToSpring("Lucas");
        
        // Then / Assert
        assertThat(filteredCourses.size(), is(4));
    }
    
    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Delete Courses not Related to Spring Using Mockito should call Method deleteCourse")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourse() {
        
        // Given / Arrange
        given(mockService.retrieveCourses("Lucas"))
            .willReturn(courses);
        
        String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
        String architectureCourse = "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#";
        String restSpringCourse = "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker";
        
        // When / Act
        business.deleteCoursesNotRelatedToSpring("Lucas");
        
        // Then / Assert
        /**verify(mockService).deleteCourse(agileCourse);
        verify(mockService, times(1)).deleteCourse(agileCourse);  
        verify(mockService, atLeastOnce()).deleteCourse(agileCourse);         
        verify(mockService).deleteCourse(architectureCourse);       
        verify(mockService, never()).deleteCourse(restSpringCourse);     
    }*/
        then(mockService).should().deleteCourse(agileCourse);   
	    then(mockService).should().deleteCourse(architectureCourse);       
	    then(mockService).should(never()).deleteCourse(restSpringCourse);                
    }
    
    @DisplayName("Delete Courses not Related to Spring Capturing Arguments should call Method deleteCourse")
    @Test
    void testDeleteCoursesNotRelatedToSpring_CapturingArguments_Should_CallMethod_deleteCourse() {
        
        // Given / Arrange
        
        /*
        courses = Arrays.asList(
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker"
            );
            */
        
        given(mockService.retrieveCourses("Lucas"))
            .willReturn(courses);
        
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        
        //String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
        
        // When / Act
        business.deleteCoursesNotRelatedToSpring("Lucas");
        
        // then(mockService).should().deleteCourse(argumentCaptor.capture());
        // assertThat(argumentCaptor.getValue(), is(agileCourse));
        
        then(mockService).should(times(7)).deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues().size(), is(7));
    }
}