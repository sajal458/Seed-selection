package CodeCrafter.example.Seed_Selection.controller;

import CodeCrafter.example.Seed_Selection.entity.Admin;
import CodeCrafter.example.Seed_Selection.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute Admin admin, HttpSession session, Model model) {
        Admin existingAdmin = adminRepository.findByUsernameAndPassword(
                admin.getUsername(), admin.getPassword());

        if (existingAdmin != null) {
            session.setAttribute("admin", existingAdmin);  // Login success
            return "redirect:/seeds";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
