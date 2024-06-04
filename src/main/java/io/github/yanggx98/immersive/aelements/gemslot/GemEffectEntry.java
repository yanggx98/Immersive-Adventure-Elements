package io.github.yanggx98.immersive.aelements.gemslot;

import net.minecraft.util.Identifier;

public class GemEffectEntry {

    private final Identifier identifier;
    private final LEVEL level;

    public GemEffectEntry(Identifier identifier,LEVEL level){
        this.identifier = identifier;
        this.level = level;
    }


    public String getTranslateKey(){
        return identifier.toTranslationKey();
    }

    public LEVEL getLevel() {
        return level;
    }

    public enum LEVEL{
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5
    }
}
