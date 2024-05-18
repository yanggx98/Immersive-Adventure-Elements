package io.github.yanggx98.immersive.aelements.gemslot;

import net.minecraft.util.Identifier;

public class GemEffectEntry {
    private final Identifier identifier;

    public GemEffectEntry(Identifier identifier){
        this.identifier = identifier;
    }

    public String getTranslateKey(){
        return identifier.toTranslationKey();
    }
}
