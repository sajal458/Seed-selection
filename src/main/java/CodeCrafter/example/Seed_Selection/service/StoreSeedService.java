package CodeCrafter.example.Seed_Selection.service;

import CodeCrafter.example.Seed_Selection.entity.StoreSeed;
import java.util.List;

public interface StoreSeedService {
    List<StoreSeed> getAllSeeds();
    StoreSeed saveSeed(StoreSeed seed);
    StoreSeed getSeedById(int id);
    void deleteSeedById(int id);
}