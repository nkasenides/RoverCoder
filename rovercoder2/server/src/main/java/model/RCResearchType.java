package model;

import java.util.HashSet;

public class RCResearchType {

    private final String name;
    private final long timeToCompleteMS;

    //Resource costs:
    private final int foodCost;
    private final int waterCost;
    private final int clayCost;
    private final int metalCost;
    private final int knowledgeCost;

    //Bonuses:
    private final int foodBonus;
    private final int waterBonus;
    private final int clayBonus;
    private final int metalBonus;
    private final int knowledgeBonus;

    //Pre-requisites:
    private final HashSet<RCResearchType> researchPrerequisites;

    RCResearchType(String name, long timeToCompleteMS, int foodCost, int waterCost, int clayCost, int metalCost, int knowledgeCost,
                   int foodBonus, int waterBonus, int clayBonus, int metalBonus, int knowledgeBonus,
                   HashSet<RCResearchType> researchPrerequisites) {
        this.name = name;
        this.timeToCompleteMS = timeToCompleteMS;
        this.foodCost = foodCost;
        this.waterCost = waterCost;
        this.clayCost = clayCost;
        this.metalCost = metalCost;
        this.knowledgeCost = knowledgeCost;
        this.foodBonus = foodBonus;
        this.waterBonus = waterBonus;
        this.clayBonus = clayBonus;
        this.metalBonus = metalBonus;
        this.knowledgeBonus = knowledgeBonus;
        this.researchPrerequisites = researchPrerequisites;
    }

    public String getName() {
        return name;
    }

    public long getTimeToCompleteMS() {
        return timeToCompleteMS;
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

    public int getFoodBonus() {
        return foodBonus;
    }

    public int getWaterBonus() {
        return waterBonus;
    }

    public int getClayBonus() {
        return clayBonus;
    }

    public int getMetalBonus() {
        return metalBonus;
    }

    public int getKnowledgeBonus() {
        return knowledgeBonus;
    }

    public HashSet<RCResearchType> getResearchPrerequisites() {
        return researchPrerequisites;
    }

    public static class Builder {

        private final String name;
        private final long timeToCompleteMS;

        //Resource costs:
        private int foodCost;
        private int waterCost;
        private int clayCost;
        private int metalCost;
        private int knowledgeCost;

        //Bonuses:
        private int foodBonus;
        private int waterBonus;
        private int clayBonus;
        private int metalBonus;
        private int knowledgeBonus;

        //Pre-requisites:
        private final HashSet<RCResearchType> researchPrerequisites = new HashSet<RCResearchType>();

        public Builder(String name, long timeToCompleteMS) {
            this.name = name;
            this.timeToCompleteMS = timeToCompleteMS;
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

        public Builder setFoodBonus(int foodBonus) {
            this.foodBonus = foodBonus;
            return this;
        }

        public Builder setWaterBonus(int waterBonus) {
            this.waterBonus = waterBonus;
            return this;
        }

        public Builder setClayBonus(int clayBonus) {
            this.clayBonus = clayBonus;
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

        public String getName() {
            return name;
        }

        public long getTimeToCompleteMS() {
            return timeToCompleteMS;
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

        public int getFoodBonus() {
            return foodBonus;
        }

        public int getWaterBonus() {
            return waterBonus;
        }

        public int getClayBonus() {
            return clayBonus;
        }

        public int getMetalBonus() {
            return metalBonus;
        }

        public int getKnowledgeBonus() {
            return knowledgeBonus;
        }

        public HashSet<RCResearchType> getResearchPrerequisites() {
            return researchPrerequisites;
        }

    }

}
