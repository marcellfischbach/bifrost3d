package com.bifrost3d.core;

import java.util.*;

public class EngineBootstrapper {

    private final Engine engine;

    private final List<IModule> modules = new ArrayList<>();

    public EngineBootstrapper(Engine engine) {
        this.engine = engine;
        collectModulesAndSortByDependencies();
    }


    private void collectModulesAndSortByDependencies() {
        Set<IModule> allModules = collectAllModules();
        while (!allModules.isEmpty()) {
            boolean oneModuleFound = false;
            for (IModule module : allModules) {
                if (allDependenciesMet(module.dependencies())) {
                    this.modules.add(module);
                    oneModuleFound = true;
                    allModules.remove(module);
                    break;
                }
            }
            if (!oneModuleFound) {
                if (!allModules.isEmpty()) {
                    throw new RuntimeException("Cyclic dependencies found");
                }
                return;
            }
        }
    }

    private Set<IModule> collectAllModules () {
        ServiceLoader<IModule> serviceLoader = ServiceLoader.load(IModule.class);
        Set<IModule> allModules = new HashSet<>();
        for (IModule module : serviceLoader) {
            allModules.add(module);
        }
        return allModules;
    }

    private boolean allDependenciesMet (Set<Class<?>> expectedDependencies) {
        Set<Class<?>> dependencies = new HashSet<>(expectedDependencies);
        for (IModule module : this.modules) {
            dependencies.removeAll(module.provides());
        }
        return dependencies.isEmpty();
    }

    public void register() {
        this.modules.forEach(mod -> mod.register(engine));
    }

    public void initialize() {
        this.modules.forEach(mod -> mod.initialize(engine));
    }

}
