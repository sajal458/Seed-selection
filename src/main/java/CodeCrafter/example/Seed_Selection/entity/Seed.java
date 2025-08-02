package CodeCrafter.example.Seed_Selection.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "seeds")
public class Seed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String crop_type;
    private String suitable_soil;
    private String suitable_region;
    private String season;
    private String description;
    private String phLevel;


    public Seed()
    {

    }
    public Seed(String name,String crop_type, String suitable_soil, String suitable_region,String season,String description,String phLevel)
    {
        this.name = name;
        this.crop_type = crop_type;
        this.suitable_soil = suitable_soil;
        this.suitable_region = suitable_region;
        this.season = season;
        this.description = description;
        this.phLevel = phLevel;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrop_type() {
        return crop_type;
    }

    public void setCrop_type(String crop_type) {
        this.crop_type = crop_type;
    }

    public String getSuitable_soil() {
        return suitable_soil;
    }

    public void setSuitable_soil(String suitable_soil) {
        this.suitable_soil = suitable_soil;
    }

    public String getSuitable_region() {
        return suitable_region;
    }

    public void setSuitable_region(String suitable_region) {
        this.suitable_region = suitable_region;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhLevel() {
        return phLevel;
    }

    public void setPhLevel(String phLevel) {
        this.phLevel = phLevel;
    }
}