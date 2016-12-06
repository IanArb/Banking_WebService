
import com.mycompany.banking_webservice.models.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 *
 * @author ianarbuckle
 */
public class CustomerTest {
    
    public Customer createCustomer() {
        return new Customer("Ham Blaster", "Bacon Street", "blaster@mail.com", "234534534");
    }
    
    @Test
    public void testCreateCustomer() {
        Customer customer = createCustomer();
        
        assertEquals("Ham Blaster", customer.getName());
        assertEquals("Bacon Street", customer.getAddress());
        assertEquals("blaster@mail.com", customer.getEmail());
        assertEquals("234534534", customer.getPhone());
    }
    
}
