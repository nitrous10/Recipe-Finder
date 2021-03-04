package com.example.cs65_final_project;

/**
 * Data type for ingredients. We can maybe create subclasses for vegetables, protein, etc. or
 * even by units (makes more sense to say 3 tomatoes than 100 grams of tomatoes)
 * */
public class Ingredient {
    private String name;
    private float amount;
    private String aisle;

    public Ingredient(String name, float amount, String aisle){
        this.name = name;
        this.amount = amount;
        this.aisle = aisleClassifier(aisle);
    }

    public Ingredient(String name, float amount){
        this.name = name;
        this.amount = amount;
    }

    public String getAisle() { return aisle; }

    public float getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    /**
     * @param apiAisle aisle as given by the api
     * @return aisle defined in our app
     */
    private String aisleClassifier(String apiAisle){
        if (apiAisle.equals("Condiments") || apiAisle.equals("Spices and Seasonings") ||
                apiAisle.equals("Oil, Vinegar, Salad Dressing")){
            return "Seasoning, Condiments, Dressings";
        }
        else if(apiAisle.equals("Meat")){
            return apiAisle;
        }
        else if(apiAisle.equals("Pasta and Rice") || apiAisle.equals("Bread") ||
                apiAisle.equals("Bakery/Bread") || apiAisle.equals("Cereal")){
            return "Carbs";
        }
        else if(apiAisle.equals("Nuts") || apiAisle.equals("Nut butters, Jam, and Honey")){
            return "Nuts";
        }
        else if(apiAisle.equals("Milk, Eggs, Other Dairy") || apiAisle.equals("Cheese")){
            return "Dairy";
        }
        else if(apiAisle.equals("Produce") || apiAisle.equals("Dried Fruits")){
            return "Fruits and Vegetables";
        }
        else if(apiAisle.equals("Seafood")){
            return apiAisle;
        }
        else if(apiAisle.equals("Tea and Coffee") || apiAisle.equals("Alcoholic Beverages") ||
                apiAisle.equals("Beverages")){
            return "Beverages";
        }
        else{
            return "Others";
        }
    }
}
