import java.util.HashMap;
import java.util.Map;

public class TicketService {

    private Map<Integer, Ticket> tickets;
    private int currentTicketId;

    public TicketService() {
        tickets = new HashMap<>();
        currentTicketId = 1;
    }

    public Ticket purchaseTicket(Event event, Customer customer) {
        Ticket ticket = new Ticket(currentTicketId, event, customer);

        // Generate a unique QR code for the ticket
        String qrCode = generateQRCode(ticket);
        ticket.setQRCode(qrCode);

        tickets.put(currentTicketId, ticket);
        currentTicketId++;
        return ticket;
    }

    public boolean verifyTicket(String qrCode, Event event, Customer customer) {
        // Find the ticket with the matching QR code
        Ticket ticket = findTicketByQRCode(qrCode);

        if (ticket != null && ticket.getEvent().equals(event) && ticket.getCustomer().equals(customer)) {
            return true;
        } else {
            return false;
        }
    }

    public void cancelTicket(int ticketId) {
        tickets.remove(ticketId);
    }

    private String generateQRCode(Ticket ticket) {
        try {
            // Set QR code properties
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.MARGIN, 0);

            // Generate QR code bit matrix
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(ticket.toString(), BarcodeFormat.QR_CODE, 200, 200, hints);

            // Convert bit matrix to QR code image as a base64 string
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
            byte[] qrCodeBytes = outputStream.toByteArray();
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(qrCodeBytes);
        } catch (WriterException | IOException e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }

    private Ticket findTicketByQRCode(String qrCode) {
        @Autowired
        private TicketRepository ticketRepository;
        byte[] qrCodeBytes = Base64.getDecoder().decode(qrCode.split(",")[1]);

        // Read QR code using zxing library
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(qrCodeBytes));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            QRCodeReader reader = new QRCodeReader();
            Result result = reader.decode(bitmap);
            String ticketString = result.getText();

            // Find ticket by QR code in the database
            return ticketRepository.findByQRCode(ticketString);
        } catch (NotFoundException | IOException | NotFoundException e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }return null;
    }
}
