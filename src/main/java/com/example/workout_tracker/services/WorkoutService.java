package com.example.workout_tracker.services;

import com.example.workout_tracker.repositories.WorkoutRepository;
import com.example.workout_tracker.entities.Workout;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout getWorkoutById(Long id) {
        Workout workout = workoutRepository.findById(id).orElse(null);
        if (workout == null) {
            throw new RuntimeException("Workout ID: " + id + " not found.");
        }
        return workout;
    }

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(Long id, Workout updatedWorkout) {
        Workout existing = getWorkoutById(id);

        existing.setTitle(updatedWorkout.getTitle());
        existing.setDate(updatedWorkout.getDate());
        existing.setMuscleGroup(updatedWorkout.getMuscleGroup());
        existing.setNotes(updatedWorkout.getNotes());
        return workoutRepository.save(existing);
    }

    public void deleteWorkout(Long id) {
        getWorkoutById(id); // Throws if ID non-existent
        workoutRepository.deleteById(id);
    }
}
