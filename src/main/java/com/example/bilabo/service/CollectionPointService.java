package com.example.bilabo.service;

import com.example.bilabo.model.CollectionPoint;
import com.example.bilabo.reporsitories.CollectionPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionPointService {

    @Autowired
    private CollectionPointRepository collectionPointRepository;

    public CollectionPoint createCollectionPoint(CollectionPoint collectionPoint) {
        return collectionPointRepository.save(collectionPoint);
    }

    public CollectionPoint getCollectionPointById(int collectionPointId) {
        return collectionPointRepository.findById(collectionPointId);
    }

    public List<CollectionPoint> getAllCollectionPoints() {
        return collectionPointRepository.findAll();
    }

    public void updateCollectionPoint(int collectionPointId, CollectionPoint updatedCollectionPoint) {
        CollectionPoint existingCollectionPoint = collectionPointRepository.findById(collectionPointId);
        if (existingCollectionPoint != null) {
            updatedCollectionPoint.setCollectionPointID(collectionPointId);
            collectionPointRepository.update(updatedCollectionPoint);
        }
    }

    public void deleteCollectionPoint(int collectionPointId) {
        collectionPointRepository.deleteById(collectionPointId);
    }
}

