/********************************************************************
 * @file: DogData.java
 * @description: This program implements the DogData class which includes the toString(), compareTo(), and equals() method
 * @author: June Bernstein
 * @date: November 14, 2024
 ******************************************************************/
public class DogData implements Comparable<DogData> { //does this need to be extends

    //from dog data
    // Breed, Country of Origin, Fur Color, Height (in), Eye Color, Longevity (yrs)
    private String breed;
    private String originCountry;
    private String furColor;
    private String height;
    private String eyeColor;
    private String longevity;


    // Implement the constructor for DogData
    public DogData(String breed, String originCountry, String furColor, String height, String eyeColor, String longevity) {
        this.breed = breed;
        this.originCountry = originCountry;
        this.furColor = furColor;
        this.height = height;
        this.eyeColor = eyeColor;
        this.longevity = longevity;
    }

    //String interface for Dog data
    @Override
    public String toString() {
        return "Breed: " + breed + ", Country of Origin: " + originCountry + ", Fur color: " + furColor + ", Height: " + height + ", Eye: " + eyeColor + ", Longevity: " + longevity;
    }

    //boolean equals method
    public boolean equals(DogData obj) {
        if (this.breed.equals(obj.breed)){
            return true;
        }
        else {
            return false;
        }
    }

    //Comparable interface for Dog Breed
    @Override
    public int compareTo(DogData obj) {

        String compared = this.breed;
        String comparedTo = obj.breed;

        int comparison = compared.compareTo(comparedTo);

        if (comparison>0){
            return +1;
        }
        else if(comparison<0){
            return -1;
        }
        else{
            return 0;
        }
    }

    //Getters and Setters for Breed
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    //Getters and Setters for Country of Origin
    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    //Getters and Setters for furColor
    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    //Getters and Setters for height
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    //getters and setters for eyeColor
    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    //getters and setters for longevity
    public String getLongevity() {
        return longevity;
    }

    public void setLongevity(String longevity) {
        this.longevity = longevity;
    }
}
