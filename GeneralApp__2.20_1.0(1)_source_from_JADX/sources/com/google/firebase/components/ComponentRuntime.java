package com.google.firebase.components;

import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-components@@16.0.0 */
public class ComponentRuntime extends AbstractComponentContainer {
    private static final Provider<Set<Object>> EMPTY_PROVIDER = ComponentRuntime$$Lambda$5.lambdaFactory$();
    private final Map<Component<?>, Lazy<?>> components = new HashMap();
    private final EventBus eventBus;
    private final Map<Class<?>, Lazy<?>> lazyInstanceMap = new HashMap();
    private final Map<Class<?>, Lazy<Set<?>>> lazySetMap = new HashMap();

    public /* bridge */ /* synthetic */ Object get(Class cls) {
        return super.get(cls);
    }

    public /* bridge */ /* synthetic */ Set setOf(Class cls) {
        return super.setOf(cls);
    }

    public ComponentRuntime(Executor defaultEventExecutor, Iterable<ComponentRegistrar> registrars, Component<?>... additionalComponents) {
        this.eventBus = new EventBus(defaultEventExecutor);
        List<Component<?>> componentsToAdd = new ArrayList<>();
        componentsToAdd.add(Component.m70of(this.eventBus, EventBus.class, Subscriber.class, Publisher.class));
        for (ComponentRegistrar registrar : registrars) {
            componentsToAdd.addAll(registrar.getComponents());
        }
        for (Component<?> additionalComponent : additionalComponents) {
            if (additionalComponent != null) {
                componentsToAdd.add(additionalComponent);
            }
        }
        CycleDetector.detect(componentsToAdd);
        for (Component<?> component : componentsToAdd) {
            this.components.put(component, new Lazy<>(ComponentRuntime$$Lambda$1.lambdaFactory$(this, component)));
        }
        processInstanceComponents();
        processSetComponents();
    }

    private void processInstanceComponents() {
        for (Entry<Component<?>, Lazy<?>> entry : this.components.entrySet()) {
            Component<?> component = (Component) entry.getKey();
            if (component.isValue()) {
                Lazy<?> lazy = (Lazy) entry.getValue();
                for (Class<?> anInterface : component.getProvidedInterfaces()) {
                    this.lazyInstanceMap.put(anInterface, lazy);
                }
            }
        }
        validateDependencies();
    }

    private void processSetComponents() {
        Map<Class<?>, Set<Lazy<?>>> setIndex = new HashMap<>();
        for (Entry<Component<?>, Lazy<?>> entry : this.components.entrySet()) {
            Component<?> component = (Component) entry.getKey();
            if (!component.isValue()) {
                Lazy<?> lazy = (Lazy) entry.getValue();
                for (Class<?> anInterface : component.getProvidedInterfaces()) {
                    if (!setIndex.containsKey(anInterface)) {
                        setIndex.put(anInterface, new HashSet());
                    }
                    ((Set) setIndex.get(anInterface)).add(lazy);
                }
            }
        }
        for (Entry<Class<?>, Set<Lazy<?>>> entry2 : setIndex.entrySet()) {
            this.lazySetMap.put((Class) entry2.getKey(), new Lazy(ComponentRuntime$$Lambda$4.lambdaFactory$((Set) entry2.getValue())));
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<com.google.firebase.components.Lazy<?>>, for r4v0, types: [java.util.Set, java.util.Set<com.google.firebase.components.Lazy<?>>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.util.Set lambda$processSetComponents$1(java.util.Set<com.google.firebase.components.Lazy<?>> r4) {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.Iterator r1 = r4.iterator()
        L_0x0009:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x001d
            java.lang.Object r2 = r1.next()
            com.google.firebase.components.Lazy r2 = (com.google.firebase.components.Lazy) r2
            java.lang.Object r3 = r2.get()
            r0.add(r3)
            goto L_0x0009
        L_0x001d:
            java.util.Set r1 = java.util.Collections.unmodifiableSet(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.components.ComponentRuntime.lambda$processSetComponents$1(java.util.Set):java.util.Set");
    }

    public <T> Provider<T> getProvider(Class<T> anInterface) {
        Preconditions.checkNotNull(anInterface, "Null interface requested.");
        return (Provider) this.lazyInstanceMap.get(anInterface);
    }

    public <T> Provider<Set<T>> setOfProvider(Class<T> anInterface) {
        Lazy<Set<?>> lazy = (Lazy) this.lazySetMap.get(anInterface);
        if (lazy != null) {
            return lazy;
        }
        return EMPTY_PROVIDER;
    }

    public void initializeEagerComponents(boolean isDefaultApp) {
        for (Entry<Component<?>, Lazy<?>> entry : this.components.entrySet()) {
            Component<?> component = (Component) entry.getKey();
            Lazy<?> lazy = (Lazy) entry.getValue();
            if (component.isAlwaysEager() || (component.isEagerInDefaultApp() && isDefaultApp)) {
                lazy.get();
            }
        }
        this.eventBus.enablePublishingAndFlushPending();
    }

    private void validateDependencies() {
        for (Component<?> component : this.components.keySet()) {
            Iterator it = component.getDependencies().iterator();
            while (true) {
                if (it.hasNext()) {
                    Dependency dependency = (Dependency) it.next();
                    if (dependency.isRequired() && !this.lazyInstanceMap.containsKey(dependency.getInterface())) {
                        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", new Object[]{component, dependency.getInterface()}));
                    }
                }
            }
        }
    }
}
