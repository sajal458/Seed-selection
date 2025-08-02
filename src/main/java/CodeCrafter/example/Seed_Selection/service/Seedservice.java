package CodeCrafter.example.Seed_Selection.service;

import CodeCrafter.example.Seed_Selection.entity.Seed;

import java.util.List;

public interface Seedservice {

    List<Seed> getAllSeeds();
    Seed saveSeed(Seed seed);
    Seed getSeedById(Integer id);
    Seed updateSeed(Seed seed);
    void deleteSeed(Integer id);
}
