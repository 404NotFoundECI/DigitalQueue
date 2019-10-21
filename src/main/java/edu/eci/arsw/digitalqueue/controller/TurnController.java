package edu.eci.arsw.digitalqueue.controller;

import edu.eci.arsw.digitalqueue.assembler.TurnRepresentationModelAssembler;
import edu.eci.arsw.digitalqueue.exception.NoTurnsInQueueException;
import edu.eci.arsw.digitalqueue.exception.TurnAlreadyCancelledException;
import edu.eci.arsw.digitalqueue.exception.TurnNotFoundException;
import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.model.Turn;
import edu.eci.arsw.digitalqueue.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "turns", produces = "application/json")
public class TurnController {

    @Autowired
    private TurnRepository turnRepository;

    @Autowired
    private TurnRepresentationModelAssembler turnRepresentationModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Turn>> all() {
        List<EntityModel<Turn>> turns = turnRepository.findAll().stream().map(turnRepresentationModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(turns, linkTo(TurnController.class).withSelfRel());
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody Turn newTurn) throws URISyntaxException {
        EntityModel<Turn> entityModel = turnRepresentationModelAssembler.toModel(turnRepository.save(newTurn));

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

    @GetMapping("/{code}")
    public EntityModel<Turn> one(@PathVariable String code) {
        Turn turn = turnRepository.findById(code).orElseThrow(() -> new TurnNotFoundException(code));

        return turnRepresentationModelAssembler.toModel(turn);
    }

    @GetMapping("/{queue}")
    public CollectionModel<EntityModel<Turn>> inQueue(@PathVariable Queue queue) {
        List<EntityModel<Turn>> turns = turnRepository.findByQueueAndAttendedFalseOrderByRequestedDateTimeDesc(queue)
                .stream().map(turnRepresentationModelAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(turns, linkTo(TurnController.class).withSelfRel());
    }

    @GetMapping("/{queue}/next")
    public EntityModel<Turn> nextInQueue(@PathVariable Queue queue) {
        Turn turn = turnRepository.findFirstByQueueAndAttendedFalseOrderByRequestedDateTimeDesc(queue)
                .orElseThrow(() -> new NoTurnsInQueueException(queue.getName()));

        return turnRepresentationModelAssembler.toModel(turn);
    }

    @PutMapping("/{code}")
    private ResponseEntity<EntityModel<Turn>> update(@PathVariable String code, @RequestBody Turn newTurn)
            throws URISyntaxException {
        Turn updatedTurn = turnRepository.findById(code).map(turn -> {
            turn.setAttended(newTurn.getAttended());
            turn.setAttendedDateTime(newTurn.getAttendedDateTime());
            turn.setAttentionPoint(newTurn.getAttentionPoint());
            turn.setCancelled(newTurn.getCancelled());
            turn.setClientName(newTurn.getClientName());
            turn.setQueue(newTurn.getQueue());
            turn.setRequestedDateTime(newTurn.getRequestedDateTime());
            return turnRepository.save(turn);
        }).orElseGet(() -> {
            newTurn.setCode(code);
            return turnRepository.save(newTurn);
        });

        EntityModel<Turn> entityModel = turnRepresentationModelAssembler.toModel(updatedTurn);

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

    @PutMapping("/{code}/cancel")
    private ResponseEntity<EntityModel<Turn>> cancel(@PathVariable String code) throws URISyntaxException {
        Turn updatedTurn = turnRepository.findById(code).orElseThrow(() -> new TurnNotFoundException(code));
        if (updatedTurn.getCancelled()) {
            throw new TurnAlreadyCancelledException(code);
        }
        updatedTurn.setCancelled(true);

        EntityModel<Turn> entityModel = turnRepresentationModelAssembler.toModel(updatedTurn);

        return ResponseEntity.created(new URI(entityModel.getRequiredLink("self").expand().getHref())).body(entityModel);
    }

}
