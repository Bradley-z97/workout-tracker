package com.example.workout_tracker.services;

import com.example.workout_tracker.entities.Workout;
import com.example.workout_tracker.repositories.WorkoutRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WorkoutServiceTest {

    @Mock
    WorkoutRepository workoutRepository;

    @InjectMocks
    WorkoutService workoutService;

    @Test
    void getWorkoutByIdReturnsWorkout() {
        Long id = 1L;
        Workout workout = new Workout();
        workout.setTitle("Leg Day");

        when(workoutRepository.findById(id)).thenReturn(Optional.of(workout));

        Workout result = workoutService.getWorkoutById(id);

        assertEquals("Leg Day", result.getTitle());
    }

    @Test
    void updateWorkoutUpdatesWorkout() {
        Long id = 1L;
        Workout workout = new Workout();
        workout.setTitle("Chest Day");

        Workout updatedWorkout = new Workout();

        updatedWorkout.setTitle("Push Day");
        updatedWorkout.setNotes("Hit new PB on bench press.");

        when(workoutRepository.findById(id)).thenReturn(Optional.of(workout));
        when(workoutRepository.save(workout)).thenReturn(workout);

        Workout result = workoutService.updateWorkout(id, updatedWorkout);

        assertEquals("Push Day", result.getTitle());
        assertEquals("Hit new PB on bench press.", result.getNotes());
    }

    @Test
    void deleteWorkoutDeletesWorkout() {
        Long id = 1L;
        Workout workout = new Workout();

        workout.setTitle("Leg day");

        when(workoutRepository.findById(id)).thenReturn(Optional.of(workout));

        workoutService.deleteWorkout(id);

        verify(workoutRepository).deleteById(id);
    }

}
