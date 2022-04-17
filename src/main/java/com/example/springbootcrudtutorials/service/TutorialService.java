package com.example.springbootcrudtutorials.service;

import com.example.springbootcrudtutorials.model.Tutorial;
import com.example.springbootcrudtutorials.repository.TutorialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TutorialService {
    @Autowired
    private TutorialRepository repository;

    public List<Tutorial> findAllTutorials(String title) {
        if (title == null) {
            return repository.findAll();
        } else {
            return repository.findByTitleContaining(title);
        }
    }

    public Optional<Tutorial> findTutorialById(long id) {
        return repository.findById(id);
    }

    public Tutorial saveTutorial(Tutorial tutorial) {
        return repository.save(tutorial);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
        log.info("Tutorial deleted : " + id);
    }

    public void deleteAll() {
        repository.deleteAll();
        log.info("All tutorials deleted");
    }

    public List<Tutorial> findAllPublishedTutorials() {
        return repository.findByPublished(true);
    }
}
