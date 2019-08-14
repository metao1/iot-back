package com.gro.web.controller;

import com.gro.model.rpicomponent.component.Relay;
import com.gro.model.rpicomponent.data.RelayDTO;
import com.gro.model.rpicomponent.data.RelayState;
import com.gro.model.rpicomponent.exception.InvalidRelayStateException;
import com.gro.model.rpicomponent.exception.RPiComponentNotFoundException;
import com.gro.repository.rpicomponent.RelayRepository;
import com.gro.web.service.RelayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/component/relay")
public class RelayController extends AbstractRestController<Relay, Integer> {

    private final Logger logger = LoggerFactory.getLogger(RelayController.class);
    @Autowired
    private RelayService relayService;

    @Value("${exception.rpi-component-not-found}")
    private String componentNotFound;

    @Value("${exception.invalid-relay-state}")
    private String invalidRelayState;

    private RelayRepository relayRepository;

    @Autowired
    public RelayController(RelayRepository repository) {
        super(repository);
        this.relayRepository = repository;
    }

    @RequestMapping(value = "/{id}/toggle", method = RequestMethod.PUT)
    public void toggleRelay(
        @PathVariable Integer id,
        @RequestParam(name = "state", required = true) String state) throws Exception {
        Optional<Relay> component = this.relayRepository.findById(id);
        RelayState relayState = this.validateRelayState(state);
        if (component.isPresent()) {
            RelayDTO relay = new RelayDTO(component.get(), relayState);
            relayService.toggle(relay);
        }

    }

    @RequestMapping(value = "/{id}/poll", method = RequestMethod.GET)
    public void pollRelay(@PathVariable("id") Integer id) throws Exception {
        Optional<Relay> component = validateRelay(id);
        RelayState relayState = null;
        if (component.isPresent()) {
            RelayDTO relay = new RelayDTO(component.get(), relayState);
            relayService.poll(relay);
        }
    }

    private Optional<Relay> validateRelay(Integer id) {
        logger.info("{}", this.relayRepository.findById(id));
        Optional<Relay> component = this.relayRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFound);
        return component;
    }

    private RelayState validateRelayState(String state) {
        RelayState relayState = RelayState.from(state);
        if (relayState == null)
            throw new InvalidRelayStateException(invalidRelayState);
        return relayState;
    }

}
