package model;

import java.util.HashSet;

public class RCBuildingType {

    public static final RCBuildingType HUB = new Builder("Hub")
            .setFoodCost(10)
            .setWaterCost(10)
            .setMetalCost(10)
            .setCitizenAddition(50)
            .build();

    public static final RCBuildingType FARM = new Builder("Farm")
            .setWaterCost(5)
            .setFoodOutput(5)
            .setCitizenOccupation(5)
            .addBuildingPrerequisite(HUB)
            .build();

    public static final RCBuildingType WELL = new Builder("Well")
            .setSandCost(5)
            .setWaterOutput(5)
            .setCitizenOccupation(5)
            .addBuildingPrerequisite(HUB)
            .build();

    public static final RCBuildingType SAND_PIT = new Builder("Sand pit")
            .setWaterCost(5)
            .setSandOutput(5)
            .setCitizenOccupation(5)
            .addBuildingPrerequisite(HUB)
            .build();

    public static final RCBuildingType MINE = new Builder("Mine")
            .setSandCost(10)
            .setWaterCost(5)
            .setFoodCost(10)
            .setMetalOutput(5)
            .setCitizenOccupation(15)
            .addBuildingPrerequisite(HUB)
            .build();

    public static final RCBuildingType SCHOOL = new Builder("School")
            .setFoodCost(10)
            .setWaterCost(5)
            .setSandCost(2)
            .setMetalCost(5)
            .setKnowledgeOutput(5)
            .setCitizenOccupation(20)
            .addBuildingPrerequisite(HUB)
            .build();

    public static final RCBuildingType GRANARY = new Builder("Granary")
            .setFoodCost(1000)
            .setWaterCost(1000)
            .setMetalCost(1000)
            .setKnowledgeCost(500)
            .setCitizenOccupation(20)
            .setBuildLimitPerHub(1)
            .addBuildingPrerequisite(FARM)
            .build();

    public static final RCBuildingType WATER_PLANT = new Builder("Water plant")
            .setWaterCost(1000)
            .setSandCost(1000)
            .setMetalCost(1000)
            .setKnowledgeCost(500)
            .setCitizenOccupation(20)
            .addBuildingPrerequisite(WELL)
            .setBuildLimitPerHub(1)
            .build();

    public static final RCBuildingType SAND_PLANT = new Builder("Sand plant")
            .setWaterCost(1000)
            .setMetalCost(1000)
            .setKnowledgeCost(1000)
            .setCitizenOccupation(20)
            .addBuildingPrerequisite(SAND_PIT)
            .setBuildLimitPerHub(1)
            .build();

    public static final RCBuildingType SMELTER = new Builder("Smelter")
            .setWaterCost(1000)
            .setMetalCost(1000)
            .setKnowledgeCost(1000)
            .setCitizenOccupation(20)
            .addBuildingPrerequisite(MINE)
            .setBuildLimitPerHub(1)
            .build();

    public static final RCBuildingType UNIVERSITY = new Builder("University")
            .setFoodCost(1000)
            .setWaterCost(1000)
            .setKnowledgeCost(1500)
            .setCitizenOccupation(20)
            .addBuildingPrerequisite(SCHOOL)
            .setBuildLimitPerHub(1)
            .build();

    public static final RCBuildingType MERCHANT = new Builder("Merchant")
            .setFoodCost(1000)
            .setWaterCost(1000)
            .setSandCost(500)
            .setMetalCost(500)
            .setCitizenOccupation(3)
            .addBuildingPrerequisite(FARM)
            .addBuildingPrerequisite(WELL)
            .addBuildingPrerequisite(SAND_PIT)
            .addBuildingPrerequisite(MINE)
            .addBuildingPrerequisite(SCHOOL)
            .setBuildLimitPerHub(3)
            .build();

    public static final RCBuildingType MARKET = new Builder("Market")
            .setFoodCost(1500)
            .setWaterCost(1500)
            .setSandCost(1500)
            .setMetalCost(1500)
            .setKnowledgeCost(1000)
            .setCitizenOccupation(20)
            .setBuildLimitPerHub(1)
            .addBuildingPrerequisite(MERCHANT)
            .build();





    private final String name;

    //Resource costs:
    private final int foodCost;
    private final int waterCost;
    private final int sandCost;
    private final int metalCost;
    private final int knowledgeCost;

    //Resource outputs:
    private final int foodOutput;
    private final int waterOutput;
    private final int sandOutput;
    private final int metalOutput;
    private final int knowledgeOutput;

    //Pre-requisites:
    private final HashSet<RCBuildingType> buildingPrerequisites;
    private final HashSet<RCResearchType> researchPrerequisites;

    //Limits:
    private final int buildLimitPerPlayer;
    private final int buildLimitPerTeam;
    private final int buildLimitPerHub;

    //Citizen addition/occupation:
    private final int citizenAddition;
    private final int citizenOccupation;

    private RCBuildingType(String name, int foodCost, int waterCost, int sandCost, int metalCost, int knowledgeCost,
                           int foodOutput, int waterOutput, int sandOutput, int metalOutput, int knowledgeOutput,
                           HashSet<RCBuildingType> buildingPrerequisites, HashSet<RCResearchType> researchPrerequisites,
                           int buildLimitPerPlayer, int buildLimitPerTeam, int buildLimitPerHub,
                           int citizenAddition, int citizenOccupation) {
        this.name = name;
        this.foodCost = foodCost;
        this.waterCost = waterCost;
        this.sandCost = sandCost;
        this.metalCost = metalCost;
        this.knowledgeCost = knowledgeCost;
        this.foodOutput = foodOutput;
        this.waterOutput = waterOutput;
        this.sandOutput = sandOutput;
        this.metalOutput = metalOutput;
        this.knowledgeOutput = knowledgeOutput;
        this.buildingPrerequisites = buildingPrerequisites;
        this.researchPrerequisites = researchPrerequisites;
        this.buildLimitPerPlayer = buildLimitPerPlayer;
        this.buildLimitPerTeam = buildLimitPerTeam;
        this.buildLimitPerHub = buildLimitPerHub;
        this.citizenAddition = citizenAddition;
        this.citizenOccupation = citizenOccupation;
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

    public int getFoodOutput() {
        return foodOutput;
    }

    public int getWaterOutput() {
        return waterOutput;
    }

    public int getSandOutput() {
        return sandOutput;
    }

    public int getMetalOutput() {
        return metalOutput;
    }

    public int getKnowledgeOutput() {
        return knowledgeOutput;
    }

    public HashSet<RCBuildingType> getBuildingPrerequisites() {
        return buildingPrerequisites;
    }

    public HashSet<RCResearchType> getResearchPrerequisites() {
        return researchPrerequisites;
    }

    public int getBuildLimitPerPlayer() {
        return buildLimitPerPlayer;
    }

    public int getBuildLimitPerTeam() {
        return buildLimitPerTeam;
    }

    public int getBuildLimitPerHub() {
        return buildLimitPerHub;
    }

    public int getCitizenAddition() {
        return citizenAddition;
    }

    public int getCitizenOccupation() {
        return citizenOccupation;
    }

    public static class Builder {

        private final String name;

        //Resource costs:
        private int foodCost = 0;
        private int waterCost = 0;
        private int sandCost = 0;
        private int metalCost = 0;
        private int knowledgeCost = 0;

        //Resource outputs:
        private int foodOutput = 0;
        private int waterOutput = 0;
        private int sandOutput  = 0;
        private int metalOutput = 0;
        private int knowledgeOutput = 0;

        //Pre-requisites:
        private final HashSet<RCBuildingType> buildingPrerequisites = new HashSet<RCBuildingType>();
        private final HashSet<RCResearchType> researchPrerequisites = new HashSet<RCResearchType>();

        private int buildLimitPerPlayer = -1;
        private int buildLimitPerTeam = -1;
        private int buildLimitPerHub = -1;

        //Citizen addition/occupation:
        private int citizenAddition = 0;
        private int citizenOccupation = 0;

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

        public Builder setFoodOutput(int foodOutput) {
            this.foodOutput = foodOutput;
            return this;
        }

        public Builder setWaterOutput(int waterOutput) {
            this.waterOutput = waterOutput;
            return this;
        }

        public Builder setSandOutput(int sandOutput) {
            this.sandOutput = sandOutput;
            return this;
        }

        public Builder setMetalOutput(int metalOutput) {
            this.metalOutput = metalOutput;
            return this;
        }

        public Builder setKnowledgeOutput(int knowledgeOutput) {
            this.knowledgeOutput = knowledgeOutput;
            return this;
        }

        public Builder addBuildingPrerequisite(RCBuildingType buildingType) {
            this.buildingPrerequisites.add(buildingType);
            return this;
        }

        public Builder addResearchPrerequisite(RCResearchType researchType) {
            this.researchPrerequisites.add(researchType);
            return this;
        }

        public Builder setBuildLimitPerPlayer(int buildLimitPerPlayer) {
            this.buildLimitPerPlayer = buildLimitPerPlayer;
            return this;
        }

        public Builder setBuildLimitPerTeam(int buildLimitPerTeam) {
            this.buildLimitPerTeam = buildLimitPerTeam;
            return this;
        }

        public Builder setBuildLimitPerHub(int buildLimitPerHub) {
            this.buildLimitPerHub = buildLimitPerHub;
            return this;
        }

        public Builder setCitizenAddition(int citizenAddition) {
            this.citizenAddition = citizenAddition;
            return this;
        }

        public Builder setCitizenOccupation(int citizenOccupation) {
            this.citizenOccupation = citizenOccupation;
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

        public int getFoodOutput() {
            return foodOutput;
        }

        public int getWaterOutput() {
            return waterOutput;
        }

        public int getSandOutput() {
            return sandOutput;
        }

        public int getMetalOutput() {
            return metalOutput;
        }

        public int getKnowledgeOutput() {
            return knowledgeOutput;
        }

        public HashSet<RCBuildingType> getBuildingPrerequisites() {
            return buildingPrerequisites;
        }

        public HashSet<RCResearchType> getResearchPrerequisites() {
            return researchPrerequisites;
        }

        public int getBuildLimitPerPlayer() {
            return buildLimitPerPlayer;
        }

        public int getBuildLimitPerTeam() {
            return buildLimitPerTeam;
        }

        public int getBuildLimitPerHub() {
            return buildLimitPerHub;
        }

        public RCBuildingType build() {
            return new RCBuildingType(name, foodCost, waterCost, sandCost, metalCost, knowledgeCost,
                    foodOutput, waterOutput, sandOutput, metalOutput, knowledgeOutput,
                    buildingPrerequisites, researchPrerequisites,
                    buildLimitPerPlayer, buildLimitPerTeam, buildLimitPerHub, citizenAddition, citizenOccupation);
        }

    }


}
