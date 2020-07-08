package com.google.firebase.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-components@@16.0.0 */
class CycleDetector {

    /* compiled from: com.google.firebase:firebase-components@@16.0.0 */
    private static class ComponentNode {
        private final Component<?> component;
        private final Set<ComponentNode> dependencies = new HashSet();
        private final Set<ComponentNode> dependents = new HashSet();

        ComponentNode(Component<?> component2) {
            this.component = component2;
        }

        /* access modifiers changed from: 0000 */
        public void addDependency(ComponentNode node) {
            this.dependencies.add(node);
        }

        /* access modifiers changed from: 0000 */
        public void addDependent(ComponentNode node) {
            this.dependents.add(node);
        }

        /* access modifiers changed from: 0000 */
        public Set<ComponentNode> getDependencies() {
            return this.dependencies;
        }

        /* access modifiers changed from: 0000 */
        public void removeDependent(ComponentNode node) {
            this.dependents.remove(node);
        }

        /* access modifiers changed from: 0000 */
        public Component<?> getComponent() {
            return this.component;
        }

        /* access modifiers changed from: 0000 */
        public boolean isRoot() {
            return this.dependents.isEmpty();
        }

        /* access modifiers changed from: 0000 */
        public boolean isLeaf() {
            return this.dependencies.isEmpty();
        }
    }

    /* compiled from: com.google.firebase:firebase-components@@16.0.0 */
    private static class Dep {
        private final Class<?> anInterface;
        /* access modifiers changed from: private */
        public final boolean set;

        private Dep(Class<?> anInterface2, boolean set2) {
            this.anInterface = anInterface2;
            this.set = set2;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof Dep)) {
                return false;
            }
            Dep dep = (Dep) obj;
            if (dep.anInterface.equals(this.anInterface) && dep.set == this.set) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return ((1000003 ^ this.anInterface.hashCode()) * 1000003) ^ Boolean.valueOf(this.set).hashCode();
        }
    }

    CycleDetector() {
    }

    static void detect(List<Component<?>> components) {
        Set<ComponentNode> graph = toGraph(components);
        Set<ComponentNode> roots = getRoots(graph);
        int numVisited = 0;
        while (!roots.isEmpty()) {
            ComponentNode node = (ComponentNode) roots.iterator().next();
            roots.remove(node);
            numVisited++;
            for (ComponentNode dependent : node.getDependencies()) {
                dependent.removeDependent(node);
                if (dependent.isRoot()) {
                    roots.add(dependent);
                }
            }
        }
        if (numVisited != components.size()) {
            List<Component<?>> componentsInCycle = new ArrayList<>();
            for (ComponentNode node2 : graph) {
                if (!node2.isRoot() && !node2.isLeaf()) {
                    componentsInCycle.add(node2.getComponent());
                }
            }
            throw new DependencyCycleException(componentsInCycle);
        }
    }

    private static Set<ComponentNode> toGraph(List<Component<?>> components) {
        Map<Dep, Set<ComponentNode>> componentIndex = new HashMap<>(components.size());
        for (Component<?> component : components) {
            ComponentNode node = new ComponentNode(component);
            Iterator it = component.getProvidedInterfaces().iterator();
            while (true) {
                if (it.hasNext()) {
                    Class<?> anInterface = (Class) it.next();
                    Dep cmp = new Dep(anInterface, !component.isValue());
                    if (!componentIndex.containsKey(cmp)) {
                        componentIndex.put(cmp, new HashSet());
                    }
                    Set<ComponentNode> nodes = (Set) componentIndex.get(cmp);
                    if (nodes.isEmpty() || cmp.set) {
                        nodes.add(node);
                    } else {
                        throw new IllegalArgumentException(String.format("Multiple components provide %s.", new Object[]{anInterface}));
                    }
                }
            }
        }
        for (Set<ComponentNode> componentNodes : componentIndex.values()) {
            for (ComponentNode node2 : componentNodes) {
                for (Dependency dependency : node2.getComponent().getDependencies()) {
                    if (dependency.isDirectInjection()) {
                        Set<ComponentNode> depComponents = (Set) componentIndex.get(new Dep(dependency.getInterface(), dependency.isSet()));
                        if (depComponents != null) {
                            for (ComponentNode depComponent : depComponents) {
                                node2.addDependency(depComponent);
                                depComponent.addDependent(node2);
                            }
                        }
                    }
                }
            }
        }
        HashSet<ComponentNode> result = new HashSet<>();
        for (Set<ComponentNode> componentNodes2 : componentIndex.values()) {
            result.addAll(componentNodes2);
        }
        return result;
    }

    private static Set<ComponentNode> getRoots(Set<ComponentNode> components) {
        Set<ComponentNode> roots = new HashSet<>();
        for (ComponentNode component : components) {
            if (component.isRoot()) {
                roots.add(component);
            }
        }
        return roots;
    }
}
