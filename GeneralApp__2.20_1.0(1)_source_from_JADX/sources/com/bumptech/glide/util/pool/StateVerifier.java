package com.bumptech.glide.util.pool;

public abstract class StateVerifier {
    private static final boolean DEBUG = false;

    private static class DebugStateVerifier extends StateVerifier {
        private volatile RuntimeException recycledAtStackTraceException;

        DebugStateVerifier() {
            super();
        }

        public void throwIfRecycled() {
            if (this.recycledAtStackTraceException != null) {
                throw new IllegalStateException("Already released", this.recycledAtStackTraceException);
            }
        }

        /* access modifiers changed from: 0000 */
        public void setRecycled(boolean isRecycled) {
            if (isRecycled) {
                this.recycledAtStackTraceException = new RuntimeException("Released");
            } else {
                this.recycledAtStackTraceException = null;
            }
        }
    }

    private static class DefaultStateVerifier extends StateVerifier {
        private volatile boolean isReleased;

        DefaultStateVerifier() {
            super();
        }

        public void throwIfRecycled() {
            if (this.isReleased) {
                throw new IllegalStateException("Already released");
            }
        }

        public void setRecycled(boolean isRecycled) {
            this.isReleased = isRecycled;
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void setRecycled(boolean z);

    public abstract void throwIfRecycled();

    public static StateVerifier newInstance() {
        return new DefaultStateVerifier();
    }

    private StateVerifier() {
    }
}
