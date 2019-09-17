package edu.eci.arsw.digitalqueue.persistence;

import edu.eci.arsw.digitalqueue.model.Queue;
import edu.eci.arsw.digitalqueue.model.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

    Optional<Turn> findByCode(String code);

    Optional<Turn> findFirstByQueueAndAttendedFalseOrderByRequestedDateTimeDesc(Queue queue);

    List<Turn> findByQueue(Queue queue);
}
