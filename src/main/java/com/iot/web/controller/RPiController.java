package com.iot.web.controller;

import com.iot.model.dto.RPiDTO;
import com.iot.model.rpi.RPi;
import com.iot.repository.BaseRepository;
import com.iot.repository.rpi.RPiRepository;
import com.iot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_NODE_ADMIN")
@RequestMapping("/api/rpi")
public class RPiController extends AbstractRestController<RPi, RPiDTO, Integer> {

    /*  @Autowired
    private StorageService storageService;*/

    @Autowired
    public RPiController(RPiRepository repository) {
        super(repository);
    }

    @Override
    public WebUtils<RPi> getWebUtils() {
        return new WebUtils<>(RPi.class);
    }

    /*@RequestMapping(value = "{id}/image", method = RequestMethod.POST)
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
    }*/

}
