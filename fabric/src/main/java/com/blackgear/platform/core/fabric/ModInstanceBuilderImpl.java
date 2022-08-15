package com.blackgear.platform.core.fabric;

import com.blackgear.platform.core.ModInstance;
import com.blackgear.platform.core.Environment;
import org.jetbrains.annotations.Nullable;

public class ModInstanceBuilderImpl {
    public static ModInstance builder(String modId, Runnable common, Runnable postCommon, Runnable client, Runnable postClient) {
        return new Fabric(modId, common, postCommon, client, postClient);
    }

    static class Fabric extends ModInstance {
        protected Fabric(String modId, @Nullable Runnable onCommon, @Nullable Runnable onPostCommon, @Nullable Runnable onClient, @Nullable Runnable onPostClient) {
            super(modId, onCommon, onPostCommon, onClient, onPostClient);
        }

        @Override
        public void bootstrap() {
            this.onCommon.run();
            this.onPostCommon.run();

            if (Environment.isClient()) {
                this.onClient.run();
                this.onPostClient.run();
            }
        }
    }
}