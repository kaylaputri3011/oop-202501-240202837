package test.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.service.CartService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {
    @Test
    public void testHitungTotal() {
        CartService cart = new CartService();
        Produk p1 = new Produk("A", "Test1", 10000, 10);
        Produk p2 = new Produk("B", "Test2", 5000, 10);

        cart.addToCart(p1, 2); // 20.000
        cart.addToCart(p2, 1); // 5.000

        assertEquals(25000, cart.calculateTotal(), "Total belanja harusnya 25.000");
    }
}