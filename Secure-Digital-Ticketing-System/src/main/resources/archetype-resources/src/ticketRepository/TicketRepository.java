@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomerId(Long customerId);

    List<Ticket> findByEventId(Long eventId);
}
