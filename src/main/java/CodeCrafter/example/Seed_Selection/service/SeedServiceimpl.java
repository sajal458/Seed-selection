package CodeCrafter.example.Seed_Selection.service;

import CodeCrafter.example.Seed_Selection.entity.Seed;
import CodeCrafter.example.Seed_Selection.repository.SeedRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeedServiceimpl implements Seedservice{

    private  SeedRepository seedRepository;

    public SeedServiceimpl(SeedRepository seedRepository) {
        this.seedRepository = seedRepository;
    }

    @Override
    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    @Override
    public Seed saveSeed(Seed seed) {
        return seedRepository.save(seed);
    }


    @Override
    public Seed getSeedById(Integer id) {
        return seedRepository.findById(id).orElse(null);
    }

    @Override
    public Seed updateSeed(Seed seed) {
        return seedRepository.save(seed);
    }

    @Override
    public void deleteSeed(Integer id) {
        seedRepository.deleteById(id);
    }

}
