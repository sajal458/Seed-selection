package CodeCrafter.example.Seed_Selection.service;

import CodeCrafter.example.Seed_Selection.entity.StoreSeed;
import CodeCrafter.example.Seed_Selection.repository.StoreSeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreSeedServiceImpl implements StoreSeedService {

    private final StoreSeedRepository storeSeedRepository;

    public StoreSeedServiceImpl(StoreSeedRepository storeSeedRepository) {
        this.storeSeedRepository = storeSeedRepository;
    }

    @Override
    public List<StoreSeed> getAllSeeds() {
        return storeSeedRepository.findAll();
    }

    @Override
    public StoreSeed saveSeed(StoreSeed seed) {
        return storeSeedRepository.save(seed);
    }

    @Override
    public StoreSeed getSeedById(int id) {
        return storeSeedRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSeedById(int id) {
        storeSeedRepository.deleteById(id);
    }
}