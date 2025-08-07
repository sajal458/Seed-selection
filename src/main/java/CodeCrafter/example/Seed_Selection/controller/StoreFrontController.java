package CodeCrafter.example.Seed_Selection.controller;

import CodeCrafter.example.Seed_Selection.entity.Order;
import CodeCrafter.example.Seed_Selection.entity.OrderItem;
import CodeCrafter.example.Seed_Selection.entity.StoreSeed;
import CodeCrafter.example.Seed_Selection.repository.OrderRepository;
import CodeCrafter.example.Seed_Selection.service.StoreSeedService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
//
//@Controller
//public class StoreFrontController {
//
//    private final StoreSeedService seedService;
//
//    public StoreFrontController(StoreSeedService seedService) {
//        this.seedService = seedService;
//    }
//
//    @GetMapping("/store")
//    public String showStore(Model model) {
//        model.addAttribute("seeds", seedService.getAllSeeds());
//        return "store";
//    }
//
//    @GetMapping("/cart")
//    public String viewCart(HttpSession session, Model model) {
//        Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
//        model.addAttribute("cart", cart != null ? cart.values() : Collections.emptyList());
//        return "cart";
//    }
//
//    @GetMapping("/cart/add/{id}")
//    public String addToCart(@PathVariable("id") int id, HttpSession session) {
//        StoreSeed seed = seedService.getSeedById(id);
//        if (seed != null) {
//            Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
//            if (cart == null) {
//                cart = new HashMap<>();
//            }
//            cart.put(id, seed);
//            session.setAttribute("cart", cart);
//        }
//        return "redirect:/store";
//    }
//
//
//    @GetMapping("/cart/remove/{id}")
//    public String removeFromCart(@PathVariable("id") int id, HttpSession session) {
//        Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
//        if (cart != null) {
//            cart.remove(id);
//            session.setAttribute("cart", cart);
//        }
//        return "redirect:/cart";
//    }
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @GetMapping("/checkout")
//    public String checkout(HttpSession session, Model model) {
//        Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
//        if (cart != null && !cart.isEmpty()) {
//            Order order = new Order();
//            order.setOrderTime(LocalDateTime.now());
//
//            List<OrderItem> items = new ArrayList<>();
//            double total = 0;
//
//            for (StoreSeed seed : cart.values()) {
//                OrderItem item = new OrderItem();
//                item.setSeedName(seed.getName());
//                item.setPrice(seed.getPrice());
//                item.setOrder(order);
//                items.add(item);
//                total += seed.getPrice();
//            }
//
//            order.setTotalAmount(total);
//            order.setItems(items);
//
//            orderRepository.save(order);
//            session.removeAttribute("cart");
//
//            model.addAttribute("total", total);
//            return "checkout";
//        }
//
//        return "redirect:/store";
//    }
//}


@Controller
public class StoreFrontController {

    private final StoreSeedService seedService;
    private final OrderRepository orderRepository;

    public StoreFrontController(StoreSeedService seedService, OrderRepository orderRepository) {
        this.seedService = seedService;
        this.orderRepository = orderRepository;
    }

//    @GetMapping("/store")
//    public String showStore(Model model) {
//        model.addAttribute("seeds", seedService.getAllSeeds());
//        return "store";
//    }

    @GetMapping("/store")
    public String showStore(Model model, HttpSession session) {
        model.addAttribute("seeds", seedService.getAllSeeds());

        Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
        model.addAttribute("cart", cart != null ? cart.values() : Collections.emptyList());

        return "store"; // the view name
    }



    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
        model.addAttribute("cart", cart != null ? cart.values() : Collections.emptyList());
        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") int id, HttpSession session) {
        StoreSeed seed = seedService.getSeedById(id);
        if (seed != null) {
            Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
            }
            cart.put(id, seed);
            session.setAttribute("cart", cart);
        }
        return "redirect:/store";
    }

//    @GetMapping("/cart/remove/{id}")
//    public String removeFromCart(@PathVariable("id") int id, HttpSession session) {
//        Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
//        if (cart != null) {
//            cart.remove(id);
//            session.setAttribute("cart", cart);
//        }
//        return "redirect:/cart";
//    }


    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") int id,
                                 @RequestParam(defaultValue = "store") String redirect,
                                 HttpSession session) {
        Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
        if (cart != null) {
            cart.remove(id);
            session.setAttribute("cart", cart);
        }

        // Dynamically redirect based on origin
        if (redirect.equals("cart")) {
            return "redirect:/cart";
        }
        return "redirect:/store";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Map<Integer, StoreSeed> cart = (Map<Integer, StoreSeed>) session.getAttribute("cart");
        if (cart != null && !cart.isEmpty()) {
            Order order = new Order();
            order.setOrderTime(LocalDateTime.now());

            List<OrderItem> items = new ArrayList<>();
            double total = 0;

            for (StoreSeed seed : cart.values()) {
                OrderItem item = new OrderItem();
                item.setSeedName(seed.getName());
                item.setPrice(seed.getPrice());
                item.setOrder(order);
                items.add(item);
                total += seed.getPrice();
            }

            order.setTotalAmount(total);
            order.setItems(items);

            orderRepository.save(order);
            session.removeAttribute("cart");

            model.addAttribute("total", total);
            return "checkout";
        }

        return "redirect:/store";
    }

    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "admin_orders";
    }

}
