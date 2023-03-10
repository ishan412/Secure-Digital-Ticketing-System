@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Customer customer;

    // getters and setters
}
