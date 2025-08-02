package CodeCrafter.example.Seed_Selection.controller;

import CodeCrafter.example.Seed_Selection.entity.Seed;
import CodeCrafter.example.Seed_Selection.service.Seedservice;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SeedController {

    private Seedservice seedService;

    public SeedController(Seedservice seedService) {
        this.seedService = seedService;
    }

    @GetMapping("/seeds")
    public String listSeeds(Model model) {
        model.addAttribute("seeds", seedService.getAllSeeds());
        return "seeds";
    }

    @GetMapping("/seeds/new")
    public String createSeedForm(Model model) {

        model.addAttribute("seed", new Seed());
        return "create_seed";
    }

    @PostMapping("/seeds")
    public String saveSeed(@ModelAttribute("seed") Seed seed) {
        seedService.saveSeed(seed);
        return "redirect:/seeds";
    }

    @GetMapping("/seeds/edit/{id}")
    public String editSeed(@PathVariable Integer id, Model model) {
        model.addAttribute("seed", seedService.getSeedById(id));
        return "edit_seed";
    }

    @PostMapping("/seeds/{id}")
    public String updateSeed(@PathVariable Integer id, @ModelAttribute("seed") Seed seed) {
        Seed existingSeed = seedService.getSeedById(id);

            existingSeed.setName(seed.getName());
            existingSeed.setCrop_type(seed.getCrop_type());
            existingSeed.setSuitable_soil(seed.getSuitable_soil());
            existingSeed.setSuitable_region(seed.getSuitable_region());
            existingSeed.setSeason(seed.getSeason());
            existingSeed.setDescription(seed.getDescription());
            existingSeed.setPhLevel(seed.getPhLevel());
            seedService.updateSeed(existingSeed);

        return "redirect:/seeds";
    }

    @GetMapping("/seeds/delete/{id}")
    public String deleteSeed(@PathVariable Integer id) {
        seedService.deleteSeed(id);
        return "redirect:/seeds";
    }

//    @GetMapping("/select-seed")
//    public String showSeedSelectionForm(Model model) {
//        model.addAttribute("seedInput", new Seed()); // Form input holder
//        return "select_seed"; // This should be a Thymeleaf form
//    }

    @GetMapping("/select-seed")
    public String showSelectionForm(Model model) {

        model.addAttribute("seedInput", new Seed());

        return "select_seed";
    }


    @PostMapping("/recommended-seeds")
    public String getRecommendedSeeds(@ModelAttribute("seedInput") Seed input, Model model) {
        List<Seed> allSeeds = seedService.getAllSeeds();

        List<Seed> recommended = allSeeds.stream()
                .filter(seed -> seed.getSuitable_soil().equalsIgnoreCase(input.getSuitable_soil()))
                .filter(seed -> seed.getSuitable_region().equalsIgnoreCase(input.getSuitable_region()))
                .filter(seed -> seed.getCrop_type().equalsIgnoreCase(input.getCrop_type()))
                .filter(seed -> seed.getSeason().equalsIgnoreCase(input.getSeason()))
                .filter(seed -> seed.getPhLevel().equalsIgnoreCase(input.getPhLevel()))
                .toList();

        model.addAttribute("recommendedSeeds", recommended);
        return "recommended_seeds";
    }


}
