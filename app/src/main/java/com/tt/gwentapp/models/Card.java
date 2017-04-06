package com.tt.gwentapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author tturcic
 *         \date 26.3.2017.
 *
 *         Json representation for following:
 *
 *         {
                "name": "Bloody Baron",
                "strength": "6",
                "group": "gold",
                "rarity": "legendary",
                "text": "Spawn a Botchling or a Lubberkin.",
                "traits": "",
                "faction": "northern realms",
                "lane": "siege",
                "loyalty": "loyal",
                "image": "https://dl.dropboxusercontent.com/u/13623665/images/gwent/bloody_baron.png",
                "flavor": "I've not been a good father, I know, but... perhaps it's not too late.",
                "tokens": "Botchling|Lubberkin"
            }
 */
public class Card extends RealmObject {

    @PrimaryKey
    private String name;
    private String strength;
    private String group;
    private String rarity;
    private String text;
    private String traits;
    private String faction;
    private String lane;
    private String loyalty;
    private String image;
    private String flavor;
    private String tokens;

    public String getName() {
        return name;
    }

    public String getStrength() {
        return strength;
    }

    public String getGroup() {
        return group;
    }

    public String getRarity() {
        return rarity;
    }

    public String getText() {
        return text;
    }

    public String getTraits() {
        return traits;
    }

    public String getFaction() {
        return faction;
    }

    public String getLane() {
        return lane;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public String getImage() {
        return image;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getTokens() {
        return tokens;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public Faction getFactionEnum(){
        switch (faction){
            case FactionName.MONSTERS:
                return Faction.MONSTERS;
            case FactionName.NEUTRAL:
                return Faction.NEUTRAL;
            case FactionName.NILFGAARD:
                return Faction.NIFLGAARD;
            case FactionName.NORTHERN_REALMS:
                return Faction.NORTHERN_REALMS;
            case FactionName.SCOIA_TAEL:
                return Faction.SCOIA_TAEL;
            case FactionName.SKELLIGE:
                return Faction.SKELLIGE;
            default:
                return Faction.ALL;
        }
    }

    public Rarity getRarityEnum(){
        switch (rarity){
            case RarityName.COMMON:
                return Rarity.COMMON;
            case RarityName.RARE:
                return Rarity.RARE;
            case RarityName.EPIC:
                return Rarity.EPIC;
            default:
                return Rarity.LEGENDARY;
        }
    }

    /**
     * These correspond to database values.
     */
    public static class FactionName {

        public static final String MONSTERS = "monsters";
        public static final String NEUTRAL = "neutral";
        public static final String NILFGAARD = "nilfgaard";
        public static final String NORTHERN_REALMS = "northern realms";
        public static final String SCOIA_TAEL = "scoia'tael";
        public static final String SKELLIGE = "skellige";

    }

    /**
     * These correspond to database values.
     */
    public static class RarityName {

        public static final String COMMON = "common";
        public static final String RARE = "rare";
        public static final String EPIC = "epic";
        public static final String LEGENDARY = "legendary";

    }
}
