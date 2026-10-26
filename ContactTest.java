import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Contact class.
 *
 * @author Duane Day Jr
 */
public class ContactTest {

    @Test
    public void testContactCreation() {
        Contact contact = new Contact("12345", "Duane", "Day", "6035551234", "123 Main Street");

        assertEquals("12345", contact.getContactId());
        assertEquals("Duane", contact.getFirstName());
        assertEquals("Day", contact.getLastName());
        assertEquals("6035551234", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    public void testContactIdCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Duane", "Day", "6035551234", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "Duane", "Day", "6035551234", "123 Main Street"));
    }

    @Test
    public void testFirstNameCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", null, "Day", "6035551234", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Christopher", "Day", "6035551234", "123 Main Street"));
    }

    @Test
    public void testLastNameCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Duane", null, "6035551234", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Duane", "Longlastname", "6035551234", "123 Main Street"));
    }

    @Test
    public void testPhoneMustContainExactlyTenDigits() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Duane", "Day", null, "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Duane", "Day", "603555123", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Duane", "Day", "603555123A", "123 Main Street"));
    }

    @Test
    public void testAddressCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Duane", "Day", "6035551234", null));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Duane", "Day", "6035551234",
                        "1234567890123456789012345678901"));
    }

    @Test
    public void testContactSetters() {
        Contact contact = new Contact("1", "Duane", "Day", "6035551234", "123 Main Street");

        contact.setFirstName("John");
        contact.setLastName("Smith");
        contact.setPhone("6035559876");
        contact.setAddress("456 Oak Street");

        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("6035559876", contact.getPhone());
        assertEquals("456 Oak Street", contact.getAddress());
    }

    @Test
    public void testMaximumLengths() {
        Contact contact = new Contact("1234567890", "1234567890", "1234567890",
                "0123456789", "123456789012345678901234567890");
        assertEquals(10, contact.getContactId().length());
        assertEquals(10, contact.getFirstName().length());
        assertEquals(10, contact.getLastName().length());
        assertEquals(30, contact.getAddress().length());
    }

    @Test
    public void testIdStaysUnchangedAfterUpdates() {
        Contact contact = new Contact("1", "Duane", "Day", "6035551234", "Main Street");
        contact.setFirstName("John");
        contact.setLastName("Smith");
        contact.setPhone("0123456789");
        contact.setAddress("Oak Street");
        assertEquals("1", contact.getContactId());
        for (java.lang.reflect.Method method : Contact.class.getMethods()) {
            org.junit.jupiter.api.Assertions.assertFalse(method.getName().equals("setContactId"));
        }
    }

    @Test
    public void testInvalidSettersKeepOriginalValues() {
        Contact contact = new Contact("1", "Duane", "Day", "6035551234", "Main Street");
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("1234567890123456789012345678901"));
        assertEquals("Duane", contact.getFirstName());
        assertEquals("Day", contact.getLastName());
        assertEquals("6035551234", contact.getPhone());
        assertEquals("Main Street", contact.getAddress());
    }
}
