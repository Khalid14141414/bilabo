package com.example.bilabo.controller;

import com.example.bilabo.model.CollectionPoint;
import com.example.bilabo.service.CollectionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectionPoints")
public class CollectionPointController {

    @Autowired
    private CollectionPointService collectionPointService;

    // Endpoint to save a new collection point
    @PostMapping
    public ResponseEntity<CollectionPoint> saveCollectionPoint(@RequestBody CollectionPoint collectionPoint) {
        CollectionPoint savedCollectionPoint = collectionPointService.createCollectionPoint(collectionPoint);
        return new ResponseEntity<>(savedCollectionPoint, HttpStatus.CREATED);
    }

    // Endpoint to update an existing collection point
    @PutMapping("/{collectionPointId}")
    public ResponseEntity<Void> updateCollectionPoint(@PathVariable int collectionPointId, @RequestBody CollectionPoint collectionPoint) {
        CollectionPoint existingCollectionPoint = collectionPointService.getCollectionPointById(collectionPointId);
        if (existingCollectionPoint != null) {
            collectionPoint.setCollectionPointID(collectionPointId);
            collectionPointService.updateCollectionPoint(collectionPointId, collectionPoint);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a collection point by its ID
    @DeleteMapping("/{collectionPointId}")
    public ResponseEntity<Void> deleteCollectionPoint(@PathVariable int collectionPointId) {
        CollectionPoint existingCollectionPoint = collectionPointService.getCollectionPointById(collectionPointId);
        if (existingCollectionPoint != null) {
            collectionPointService.deleteCollectionPoint(collectionPointId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to retrieve a collection point by its ID
    @GetMapping("/{collectionPointId}")
    public ResponseEntity<CollectionPoint> getCollectionPointById(@PathVariable int collectionPointId) {
        CollectionPoint collectionPoint = collectionPointService.getCollectionPointById(collectionPointId);
        if (collectionPoint != null) {
            return new ResponseEntity<>(collectionPoint, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to retrieve all collection points
    @GetMapping
    public ResponseEntity<List<CollectionPoint>> getAllCollectionPoints() {
        List<CollectionPoint> collectionPoints = collectionPointService.getAllCollectionPoints();
        return new ResponseEntity<>(collectionPoints, HttpStatus.OK);
    }
}

