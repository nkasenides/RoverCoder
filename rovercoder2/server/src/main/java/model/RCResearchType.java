package model;

import java.util.HashSet;

public class RCResearchType {

    public static final RCResearchType MATHEMATICS = new Builder("Mathematics")
            .setFoodCost(500)
            .setKnowledgeCost(1000)
            .build();

    public static final RCResearchType WRITTEN_WORD = new Builder("Written word")
            .setFoodCost(500)
            .setKnowledgeCost(1000)
            .build();

    public static final RCResearchType BIOLOGY = new Builder("Biology")
            .setFoodCost(500)
            .setKnowledgeCost(1500)
            .addResearchPrerequisite(MATHEMATICS)
            .build();

    public static final RCResearchType CHEMISTRY = new Builder("Chemistry")
            .setFoodCost(500)
            .setKnowledgeCost(1500)
            .addResearchPrerequisite(MATHEMATICS)
            .build();

    public static final RCResearchType PHYSICS = new Builder("Physics")
            .setFoodCost(500)
            .setKnowledgeCost(1500)
            .addResearchPrerequisite(MATHEMATICS)
            .build();

    public static final RCResearchType BASIC_COMPUTING = new Builder("Computing")
            .setFoodCost(500)
            .setKnowledgeCost(2000)
            .addResearchPrerequisite(MATHEMATICS)
            .addResearchPrerequisite(PHYSICS)
            .build();

    public static final RCResearchType POTATO_CULTIVATION = new Builder("Potato cultivation")
            .setFoodCost(1500)
            .setWaterCost(1500)
            .setSandCost(1500)
            .setKnowledgeCost(3000)
            .setFoodBonus(1000)
            .setFoodGatherMultiplier(1.05)
            .addResearchPrerequisite(BIOLOGY)
            .build();

    public static final RCResearchType WHEAT_CULTIVATION = new Builder("Wheat cultivation")
            .setFoodCost(1500)
            .setWaterCost(1500)
            .setSandCost(1500)
            .setKnowledgeCost(3000)
            .setFoodBonus(1000)
            .setFoodGatherMultiplier(1.05)
            .addResearchPrerequisite(BIOLOGY)
            .build();

    public static final RCResearchType RICE_CULTIVATION = new Builder("Rice cultivation")
            .setFoodCost(1500)
            .setWaterCost(1500)
            .setSandCost(1500)
            .setKnowledgeCost(3000)
            .setFoodBonus(1000)
            .setFoodGatherMultiplier(1.05)
            .addResearchPrerequisite(BIOLOGY)
            .build();

    //...

    public static final RCResearchType DISINFECTION = new Builder("Disinfection")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setWaterBonus(1000)
            .setWaterGatherMultiplier(1.05)
            .addResearchPrerequisite(CHEMISTRY)
            .build();

    public static final RCResearchType SEDIMENTATION = new Builder("Sedimentation")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setWaterBonus(1000)
            .setWaterGatherMultiplier(1.05)
            .addResearchPrerequisite(CHEMISTRY)
            .build();

    public static final RCResearchType FILTRATION = new Builder("Filtration")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setWaterBonus(1000)
            .setWaterGatherMultiplier(1.05)
            .addResearchPrerequisite(CHEMISTRY)
            .addResearchPrerequisite(PHYSICS)
            .build();

    //...

    public static final RCResearchType DECOMPOSITION = new Builder("Decomposition")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setSandBonus(1000)
            .setSandGatherMultiplier(1.05)
            .addResearchPrerequisite(CHEMISTRY)
            .addResearchPrerequisite(PHYSICS)
            .build();

    public static final RCResearchType AUTOMATIC_DREDGER = new Builder("Automatic dredger")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setSandBonus(1000)
            .setSandGatherMultiplier(1.05)
            .addResearchPrerequisite(BASIC_COMPUTING)
            .build();

    public static final RCResearchType AUTOMATIC_SORTER = new Builder("Automatic sorter")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setSandBonus(1000)
            .setSandGatherMultiplier(1.05)
            .addResearchPrerequisite(BASIC_COMPUTING)
            .build();

    //...

    public static final RCResearchType ALLOYS = new Builder("Alloys")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setMetalBonus(1000)
            .setMetalGatherMultiplier(1.05)
            .addResearchPrerequisite(CHEMISTRY)
            .build();

    public static final RCResearchType CASTING = new Builder("Casting")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setMetalBonus(1000)
            .setMetalGatherMultiplier(1.05)
            .addResearchPrerequisite(CHEMISTRY)
            .build();

    public static final RCResearchType STEEL = new Builder("Steel")
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(3000)
            .setMetalBonus(1000)
            .setMetalGatherMultiplier(1.05)
            .addResearchPrerequisite(CHEMISTRY)
            .build();

    //...

    public static final RCResearchType LITERACY = new Builder("Literacy")
            .setFoodCost(3000)
            .setKnowledgeCost(5000)
            .setKnowledgeBonus(1000)
            .setKnowledgeGatherMultiplier(1.05)
            .addResearchPrerequisite(WRITTEN_WORD)
            .build();

    public static final RCResearchType SCIENTIFIC_METHOD = new Builder("Scientific method")
            .setFoodCost(3000)
            .setKnowledgeCost(7000)
            .setKnowledgeBonus(1000)
            .setKnowledgeGatherMultiplier(1.05)
            .addResearchPrerequisite(LITERACY)
            .build();

    public static final RCResearchType INSTITUTIONAL_RESEARCH = new Builder("Institutional research")
            .setFoodCost(3000)
            .setKnowledgeCost(9000)
            .setKnowledgeBonus(1000)
            .setKnowledgeGatherMultiplier(1.05)
            .addResearchPrerequisite(SCIENTIFIC_METHOD)
            .build();

    //...



    private final String name;

    //Resource costs:
    private final int foodCost;
    private final int waterCost;
    private final int sandCost;
    private final int metalCost;
    private final int knowledgeCost;

    //Bonuses:
    private final int foodBonus;
    private final int waterBonus;
    private final int sandBonus;
    private final int metalBonus;
    private final int knowledgeBonus;

    //Multipliers:
    private final double foodGatherMultiplier;
    private final double waterGatherMultiplier;
    private final double sandGatherMultiplier;
    private final double metalGatherMultiplier;
    private final double knowledgeGatherMultiplier;

    private final double buildingCostMultiplier;

    //Pre-requisites:
    private final HashSet<RCResearchType> researchPrerequisites;

    RCResearchType(String name, int foodCost, int waterCost, int sandCost, int metalCost, int knowledgeCost,
                   int foodBonus, int waterBonus, int sandBonus, int metalBonus, int knowledgeBonus,
                   double foodGatherMultiplier, double waterGatherMultiplier, double sandGatherMultiplier, double metalGatherMultiplier,
                   double knowledgeGatherMultiplier, double buildingCostMultiplier,
                   HashSet<RCResearchType> researchPrerequisites

    ) {
        this.name = name;
        this.foodCost = foodCost;
        this.waterCost = waterCost;
        this.sandCost = sandCost;
        this.metalCost = metalCost;
        this.knowledgeCost = knowledgeCost;
        this.foodBonus = foodBonus;
        this.waterBonus = waterBonus;
        this.sandBonus = sandBonus;
        this.metalBonus = metalBonus;
        this.knowledgeBonus = knowledgeBonus;
        this.foodGatherMultiplier = foodGatherMultiplier;
        this.waterGatherMultiplier = waterGatherMultiplier;
        this.sandGatherMultiplier = sandGatherMultiplier;
        this.metalGatherMultiplier = metalGatherMultiplier;
        this.knowledgeGatherMultiplier = knowledgeGatherMultiplier;
        this.buildingCostMultiplier = buildingCostMultiplier;
        this.researchPrerequisites = researchPrerequisites;
    }

    public String getName() {
        return name;
    }

    public int getFoodCost() {
        return foodCost;
    }

    public int getWaterCost() {
        return waterCost;
    }

    public int getSandCost() {
        return sandCost;
    }

    public int getMetalCost() {
        return metalCost;
    }

    public int getKnowledgeCost() {
        return knowledgeCost;
    }

    public int getFoodBonus() {
        return foodBonus;
    }

    public int getWaterBonus() {
        return waterBonus;
    }

    public int getSandBonus() {
        return sandBonus;
    }

    public int getMetalBonus() {
        return metalBonus;
    }

    public int getKnowledgeBonus() {
        return knowledgeBonus;
    }

    public double getFoodGatherMultiplier() {
        return foodGatherMultiplier;
    }

    public double getWaterGatherMultiplier() {
        return waterGatherMultiplier;
    }

    public double getSandGatherMultiplier() {
        return sandGatherMultiplier;
    }

    public double getMetalGatherMultiplier() {
        return metalGatherMultiplier;
    }

    public double getKnowledgeGatherMultiplier() {
        return knowledgeGatherMultiplier;
    }

    public double getBuildingCostMultiplier() {
        return buildingCostMultiplier;
    }

    public HashSet<RCResearchType> getResearchPrerequisites() {
        return researchPrerequisites;
    }

    public static class Builder {

        private final String name;

        //Resource costs:
        private int foodCost;
        private int waterCost;
        private int sandCost;
        private int metalCost;
        private int knowledgeCost;

        //Bonuses:
        private int foodBonus;
        private int waterBonus;
        private int sandBonus;
        private int metalBonus;
        private int knowledgeBonus;

        //Multipliers:
        private double foodGatherMultiplier;
        private double waterGatherMultiplier;
        private double sandGatherMultiplier;
        private double metalGatherMultiplier;
        private double knowledgeGatherMultiplier;

        private double buildingCostMultiplier;

        //Pre-requisites:
        private final HashSet<RCResearchType> researchPrerequisites = new HashSet<RCResearchType>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder setFoodCost(int foodCost) {
            this.foodCost = foodCost;
            return this;
        }

        public Builder setWaterCost(int waterCost) {
            this.waterCost = waterCost;
            return this;
        }

        public Builder setSandCost(int sandCost) {
            this.sandCost = sandCost;
            return this;
        }

        public Builder setMetalCost(int metalCost) {
            this.metalCost = metalCost;
            return this;
        }

        public Builder setKnowledgeCost(int knowledgeCost) {
            this.knowledgeCost = knowledgeCost;
            return this;
        }

        public Builder setFoodBonus(int foodBonus) {
            this.foodBonus = foodBonus;
            return this;
        }

        public Builder setWaterBonus(int waterBonus) {
            this.waterBonus = waterBonus;
            return this;
        }

        public Builder setSandBonus(int sandBonus) {
            this.sandBonus = sandBonus;
            return this;
        }

        public Builder setMetalBonus(int metalBonus) {
            this.metalBonus = metalBonus;
            return this;
        }

        public Builder setKnowledgeBonus(int knowledgeBonus) {
            this.knowledgeBonus = knowledgeBonus;
            return this;
        }

        public Builder addResearchPrerequisite(RCResearchType researchType) {
            this.researchPrerequisites.add(researchType);
            return this;
        }

        public Builder setFoodGatherMultiplier(double foodGatherMultiplier) {
            this.foodGatherMultiplier = foodGatherMultiplier;
            return this;
        }

        public Builder setWaterGatherMultiplier(double waterGatherMultiplier) {
            this.waterGatherMultiplier = waterGatherMultiplier;
            return this;
        }

        public Builder setSandGatherMultiplier(double sandGatherMultiplier) {
            this.sandGatherMultiplier = sandGatherMultiplier;
            return this;
        }

        public Builder setMetalGatherMultiplier(double metalGatherMultiplier) {
            this.metalGatherMultiplier = metalGatherMultiplier;
            return this;
        }

        public Builder setKnowledgeGatherMultiplier(double knowledgeGatherMultiplier) {
            this.knowledgeGatherMultiplier = knowledgeGatherMultiplier;
            return this;
        }

        public Builder setBuildingCostMultiplier(double buildingCostMultiplier) {
            this.buildingCostMultiplier = buildingCostMultiplier;
            return this;
        }

        public String getName() {
            return name;
        }

        public int getFoodCost() {
            return foodCost;
        }

        public int getWaterCost() {
            return waterCost;
        }

        public int getSandCost() {
            return sandCost;
        }

        public int getMetalCost() {
            return metalCost;
        }

        public int getKnowledgeCost() {
            return knowledgeCost;
        }

        public int getFoodBonus() {
            return foodBonus;
        }

        public int getWaterBonus() {
            return waterBonus;
        }

        public int getSandBonus() {
            return sandBonus;
        }

        public int getMetalBonus() {
            return metalBonus;
        }

        public int getKnowledgeBonus() {
            return knowledgeBonus;
        }

        public double getFoodGatherMultiplier() {
            return foodGatherMultiplier;
        }

        public double getWaterGatherMultiplier() {
            return waterGatherMultiplier;
        }

        public double getSandGatherMultiplier() {
            return sandGatherMultiplier;
        }

        public double getMetalGatherMultiplier() {
            return metalGatherMultiplier;
        }

        public double getKnowledgeGatherMultiplier() {
            return knowledgeGatherMultiplier;
        }

        public double getBuildingCostMultiplier() {
            return buildingCostMultiplier;
        }

        public HashSet<RCResearchType> getResearchPrerequisites() {
            return researchPrerequisites;
        }

        public RCResearchType build() {
            return new RCResearchType(name, foodCost, waterCost, sandCost, metalCost, knowledgeCost, foodBonus, waterBonus, sandBonus, metalBonus, knowledgeBonus,
                    foodGatherMultiplier, waterGatherMultiplier, sandGatherMultiplier, metalGatherMultiplier, knowledgeGatherMultiplier, buildingCostMultiplier,
                    researchPrerequisites);
        }

    }

}
