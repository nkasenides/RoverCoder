package model;

import java.util.HashSet;

public class RCBuildingType {

    private final String name;

    //Resource costs:
    private final int foodCost;
    private final int waterCost;
    private final int clayCost;
    private final int metalCost;
    private final int knowledgeCost;

    //Resource outputs:
    private final int foodOutput;
    private final int waterOutput;
    private final int clayOutput;
    private final int metalOutput;
    private final int knowledgeOutput;

    //Pre-requisites:
    private final HashSet<RCBuildingType> buildingPrerequisites;
    private final HashSet<RCResearchType> researchPrerequisites;

    //Limits:
    private final int buildLimitPerPlayer;
    private final int buildLimitPerTeam;

    public RCBuildingType(String name, int foodCost, int waterCost, int clayCost, int metalCost, int knowledgeCost,
                          int foodOutput, int waterOutput, int clayOutput, int metalOutput, int knowledgeOutput,
                          HashSet<RCBuildingType> buildingPrerequisites, HashSet<RCResearchType> researchPrerequisites,
                          int buildLimitPerPlayer, int buildLimitPerTeam) {
        this.name = name;
        this.foodCost = foodCost;
        this.waterCost = waterCost;
        this.clayCost = clayCost;
        this.metalCost = metalCost;
        this.knowledgeCost = knowledgeCost;
        this.foodOutput = foodOutput;
        this.waterOutput = waterOutput;
        this.clayOutput = clayOutput;
        this.metalOutput = metalOutput;
        this.knowledgeOutput = knowledgeOutput;
        this.buildingPrerequisites = buildingPrerequisites;
        this.researchPrerequisites = researchPrerequisites;
        this.buildLimitPerPlayer = buildLimitPerPlayer;
        this.buildLimitPerTeam = buildLimitPerTeam;
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

    public int getClayCost() {
        return clayCost;
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

    public int getClayOutput() {
        return clayOutput;
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

    public static class Builder {

        private final String name;

        //Resource costs:
        private int foodCost = 0;
        private int waterCost = 0;
        private int clayCost = 0;
        private int metalCost = 0;
        private int knowledgeCost = 0;

        //Resource outputs:
        private int foodOutput = 0;
        private int waterOutput = 0;
        private int clayOutput = 0;
        private int metalOutput = 0;
        private int knowledgeOutput = 0;

        //Pre-requisites:
        private final HashSet<RCBuildingType> buildingPrerequisites = new HashSet<RCBuildingType>();
        private final HashSet<RCResearchType> researchPrerequisites = new HashSet<RCResearchType>();

        private int buildLimitPerPlayer = -1;
        private int buildLimitPerTeam = -1;

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

        public Builder setClayCost(int clayCost) {
            this.clayCost = clayCost;
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

        public Builder setClayOutput(int clayOutput) {
            this.clayOutput = clayOutput;
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

        public String getName() {
            return name;
        }

        public int getFoodCost() {
            return foodCost;
        }

        public int getWaterCost() {
            return waterCost;
        }

        public int getClayCost() {
            return clayCost;
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

        public int getClayOutput() {
            return clayOutput;
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

        public RCBuildingType build() {
            return new RCBuildingType(name, foodCost, waterCost, clayCost, metalCost, knowledgeCost, foodOutput, waterOutput, clayOutput, metalOutput, knowledgeOutput, buildingPrerequisites, researchPrerequisites, buildLimitPerPlayer, buildLimitPerTeam);
        }

    }


}
