@Entity
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    // getters and setters
}
