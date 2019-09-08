package com.iot.web.controller;

import com.iot.model.dto.RPiDTO;
import com.iot.model.rpi.RPi;
import com.iot.model.rpicomponent.exception.EntityNotFoundException;
import com.iot.repository.rpi.RPiRepository;
import com.iot.utils.WebUtils;
import com.iot.web.service.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/rpi")
public class RPiController extends AbstractRestController<RPi, RPiDTO, Integer> {

    private final Logger LOG = LoggerFactory.getLogger(RPiController.class);

    @Autowired
    private StorageService storageService;

    @Autowired
    public RPiController(RPiRepository repository) {
        super(repository);
    }

    @Override
    public WebUtils<RPi> getWebUtils() {
        return new WebUtils<>(RPi.class);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public String download(){
        return "download";
    }

    @PostMapping(value = "/{id}/image")
    @ResponseStatus(HttpStatus.OK)
    public void saveImage(@PathVariable("id") int id,  @RequestParam("file") MultipartFile file) {
        LOG.debug("Upload {} start for {}",id ,  file.getName());
        Optional<RPi> rpi = this.repository.findById(id);
        if (rpi.isPresent()) {
            this.storageService.store(file);
            rpi.get().setImageUrl(file.getOriginalFilename());
            this.repository.save(rpi.get());
        } else {
            throw new EntityNotFoundException("RPi with supplied id was not found");
        }
    }

}
