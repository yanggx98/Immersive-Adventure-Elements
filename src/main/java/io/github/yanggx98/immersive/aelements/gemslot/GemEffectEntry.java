package io.github.yanggx98.immersive.aelements.gemslot;

import com.sun.jna.platform.win32.COM.util.Factory;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemEffectEntry {

    protected final Identifier identifier;

    public GemEffectEntry(Identifier identifier) {
        this.identifier = identifier;
    }


    public String getTranslateKey() {
        return identifier.toTranslationKey();
    }

    public Text getDescText(GemItem.Level level) {
        return Text.translatable(identifier.withSuffixedPath(".desc").toTranslationKey());
    }

}
