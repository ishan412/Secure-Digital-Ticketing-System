@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStartDateTimeAfter(LocalDateTime startDateTime);

    List<Event> findByEndDateTimeBefore(LocalDateTime endDateTime);
}
