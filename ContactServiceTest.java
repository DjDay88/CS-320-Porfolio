import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ContactService class.
 *
 * @author Duane Day Jr
 */
public class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
        contact = new Contact("12345", "Duane", "Day", "6035551234", "123 Main Street");
    }

    @Test
    public void testAddContact() {
        service.addContact(contact);
        assertEquals(contact, service.getContact("12345"));
    }

    @Test
    public void testDuplicateContactIdIsRejected() {
        service.addContact(contact);
        Contact duplicate = new Contact("12345", "John", "Smith", "6035559876", "456 Oak Street");

        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicate));
    }

    @Test
    public void testDeleteContact() {
        service.addContact(contact);
        service.deleteContact("12345");

        assertThrows(IllegalArgumentException.class, () -> service.getContact("12345"));
    }

    @Test
    public void testDeleteMissingContactIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("missing"));
    }

    @Test
    public void testUpdateFirstName() {
        service.addContact(contact);
        service.updateFirstName("12345", "John");
        assertEquals("John", service.getContact("12345").getFirstName());
    }

    @Test
    public void testUpdateLastName() {
        service.addContact(contact);
        service.updateLastName("12345", "Smith");
        assertEquals("Smith", service.getContact("12345").getLastName());
    }

    @Test
    public void testUpdatePhone() {
        service.addContact(contact);
        service.updatePhone("12345", "6035559876");
        assertEquals("6035559876", service.getContact("12345").getPhone());
    }

    @Test
    public void testUpdateAddress() {
        service.addContact(contact);
        service.updateAddress("12345", "456 Oak Street");
        assertEquals("456 Oak Street", service.getContact("12345").getAddress());
    }

    @Test
    public void testInvalidUpdateIsRejected() {
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class,
                () -> service.updatePhone("12345", "123"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateFirstName("missing", "John"));
    }

    @Test
    public void testMultipleUniqueIdsAndDeletion() {
        Contact second = new Contact("2", "John", "Smith", "0123456789", "Oak Street");
        service.addContact(contact);
        service.addContact(second);
        service.deleteContact("12345");
        assertEquals(second, service.getContact("2"));
        assertThrows(IllegalArgumentException.class, () -> service.getContact("12345"));
        service.addContact(contact);
        assertEquals(contact, service.getContact("12345"));
    }

    @Test
    public void testNullContactIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }
}
