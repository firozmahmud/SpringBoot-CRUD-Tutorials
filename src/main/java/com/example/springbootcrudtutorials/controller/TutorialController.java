package com.example.springbootcrudtutorials.controller;

import com.example.springbootcrudtutorials.model.Tutorial;
import com.example.springbootcrudtutorials.service.TutorialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    private TutorialService service;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = service.findAllTutorials(title);
        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = service.findTutorialById(id);
        return tutorialData.map(tutorial -> new ResponseEntity<>(tutorial, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial responseData = service.saveTutorial(tutorial);
        if (responseData == null) {
            log.error("Response data is null");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteTutorial(@RequestParam() long id) {
        log.info("Delete tutorial by id method called");
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/all")
    public ResponseEntity<HttpStatus> deleteAllTutorial() {
        log.info("Delete all tutorial method called");
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> getAllPublishedTutorials() {
        List<Tutorial> tutorials = service.findAllPublishedTutorials();
        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }
    }
}
