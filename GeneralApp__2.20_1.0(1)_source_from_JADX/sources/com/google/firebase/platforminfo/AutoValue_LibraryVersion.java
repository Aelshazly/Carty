package com.google.firebase.platforminfo;

import javax.annotation.Nonnull;

/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
final class AutoValue_LibraryVersion extends LibraryVersion {
    private final String libraryName;
    private final String version;

    AutoValue_LibraryVersion(String libraryName2, String version2) {
        if (libraryName2 != null) {
            this.libraryName = libraryName2;
            if (version2 != null) {
                this.version = version2;
                return;
            }
            throw new NullPointerException("Null version");
        }
        throw new NullPointerException("Null libraryName");
    }

    @Nonnull
    public String getLibraryName() {
        return this.libraryName;
    }

    @Nonnull
    public String getVersion() {
        return this.version;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LibraryVersion{libraryName=");
        sb.append(this.libraryName);
        sb.append(", version=");
        sb.append(this.version);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof LibraryVersion)) {
            return false;
        }
        LibraryVersion that = (LibraryVersion) o;
        if (!this.libraryName.equals(that.getLibraryName()) || !this.version.equals(that.getVersion())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.libraryName.hashCode()) * 1000003) ^ this.version.hashCode();
    }
}