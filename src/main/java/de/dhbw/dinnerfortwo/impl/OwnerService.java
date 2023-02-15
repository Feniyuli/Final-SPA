package de.dhbw.dinnerfortwo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * The OwnerService contains the operations related to managing Owners.
 */
@Service
public class OwnerService {
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    private final OwnerRepository ownerRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Owner getOwner(UUID id) {
        log.info("Looking for an owner with id {}", id);
        return ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find owner with Id " + id));
    }

    public List<Owner> getAllOwners() {
        log.info("Get all owners");
        return ownerRepository.findAll().stream().toList();
    }

    public Owner createOrUpdate(Owner owner) {
        log.info("Save or update owner {}", owner);
        return ownerRepository.save(owner);
    }

    public void delete(UUID id) {
        log.info("Deleting owner with id {}", id);
        ownerRepository.deleteById(id);
    }
}
