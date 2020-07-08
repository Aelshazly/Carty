package dagger.internal;

public final class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(errorMessage);
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object errorMessageArg) {
        String argString;
        if (reference != null) {
            return reference;
        }
        String str = "%s";
        if (!errorMessageTemplate.contains(str)) {
            throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
        } else if (errorMessageTemplate.indexOf(str) == errorMessageTemplate.lastIndexOf(str)) {
            if (errorMessageArg instanceof Class) {
                argString = ((Class) errorMessageArg).getCanonicalName();
            } else {
                argString = String.valueOf(errorMessageArg);
            }
            throw new NullPointerException(errorMessageTemplate.replace(str, argString));
        } else {
            throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
        }
    }

    public static <T> void checkBuilderRequirement(T requirement, Class<T> clazz) {
        if (requirement == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(clazz.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }
    }

    private Preconditions() {
    }
}
