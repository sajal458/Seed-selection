//package CodeCrafter.example.Seed_Selection.controller;
//
//import CodeCrafter.example.Seed_Selection.entity.StoreSeed;
//import CodeCrafter.example.Seed_Selection.service.StoreSeedService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/admin/seeds")
//public class AdminSeedController {
//
//    private final StoreSeedService seedService;
//
//    @Value("${upload.dir}")
//    private String uploadDir;
//
//    public AdminSeedController(StoreSeedService seedService) {
//        this.seedService = seedService;
//    }
//
//    @GetMapping
//    public String listSeeds(Model model) {
//        model.addAttribute("seeds", seedService.getAllSeeds());
//        return "admin_seeds";
//    }
//
//    @GetMapping("/new")
//    public String createSeedForm(Model model) {
//        model.addAttribute("seed", new StoreSeed());
//        return "admin_seed_form";
//    }
//
//    @PostMapping
//    public String saveSeed(
//            @ModelAttribute StoreSeed seed,
//            @RequestParam("imageFile") MultipartFile file,
//            @RequestParam(value = "existingImage", required = false) String existingImage
//    ) throws IOException {
//        if (!file.isEmpty()) {
//            File folder = new File(uploadDir);
//            if (!folder.exists()) folder.mkdirs();
//
//            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//            file.transferTo(new File(uploadDir + "/" + fileName));
//            seed.setImagePath(fileName);
//        } else {
//
//            seed.setImagePath(existingImage);
//        }
//
//        seedService.saveSeed(seed);
//        return "redirect:/admin/seeds";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editSeedForm(@PathVariable("id") int id, Model model) {
//        StoreSeed seed = seedService.getSeedById(id);
//        model.addAttribute("seed", seed);
//        return "admin_seed_form";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteSeed(@PathVariable("id") int id) {
//        seedService.deleteSeedById(id);
//        return "redirect:/admin/seeds";
//    }
//}





package CodeCrafter.example.Seed_Selection.controller;

import CodeCrafter.example.Seed_Selection.entity.StoreSeed;
import CodeCrafter.example.Seed_Selection.service.StoreSeedService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin/seeds")
public class AdminSeedController {

    private final StoreSeedService seedService;

    @Value("${upload.dir}")
    private String uploadDir;

    public AdminSeedController(StoreSeedService seedService) {
        this.seedService = seedService;
    }

    @GetMapping
    public String listSeeds(Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        model.addAttribute("seeds", seedService.getAllSeeds());
        return "admin_seeds";
    }

    @GetMapping("/new")
    public String createSeedForm(Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        model.addAttribute("seed", new StoreSeed());
        return "admin_seed_form";
    }

    @PostMapping
    public String saveSeed(
            @ModelAttribute StoreSeed seed,
            @RequestParam("imageFile") MultipartFile file,
            @RequestParam(value = "existingImage", required = false) String existingImage,
            HttpSession session
    ) throws IOException {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        if (!file.isEmpty()) {
            File folder = new File(uploadDir);
            if (!folder.exists()) folder.mkdirs();

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + "/" + fileName));
            seed.setImagePath(fileName);
        } else {
            seed.setImagePath(existingImage);
        }

        seedService.saveSeed(seed);
        return "redirect:/admin/seeds";
    }

    @GetMapping("/edit/{id}")
    public String editSeedForm(@PathVariable("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        StoreSeed seed = seedService.getSeedById(id);
        model.addAttribute("seed", seed);
        return "admin_seed_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSeed(@PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        seedService.deleteSeedById(id);
        return "redirect:/admin/seeds";
    }
}
