package com.gro.web.controller;

import com.gro.model.rpi.RPi;
import com.gro.model.rpicomponent.exception.EntityNotFoundException;
import com.gro.repository.rpi.RPiRepository;
import com.gro.web.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rpi")
public class RPiController extends AbstractRestController<RPi, Integer> {

    @Autowired
    private StorageService storageService;

    @Autowired
    public RPiController(RPiRepository repository) {
        super(repository);
    }

    @RequestMapping(value = "{id}/image", method = RequestMethod.POST)
    public Map<String, Object> saveImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        Optional<RPi> rpi = this.repository.findById(id);
        if (rpi.isPresent()) {
            this.storageService.store(file);
            rpi.get().setImageUrl(file.getOriginalFilename());
            this.repository.save(rpi.get());
        } else {
            throw new EntityNotFoundException("RPi with supplied id was not found");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

}
