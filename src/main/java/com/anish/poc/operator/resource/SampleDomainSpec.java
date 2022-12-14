package com.anish.poc.operator.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class SampleDomainSpec {

    @JsonProperty("application-image")
    private String applicationImage;

    @JsonProperty("instance-image")
    private String instanceImage;

    @JsonProperty("application")
    private String application;

    @JsonProperty("instances")
    private int instances;

    @JsonProperty("config-script")
    private String configScript;

    @JsonProperty("cpu")
    private int cpu;

    @JsonProperty("memory")
    private int memory;

    @JsonProperty("max-instances")
    private int maxInstances;

    @JsonProperty("cpu-target")
    private int cpuTarget;

    @JsonProperty("verbose")
    private boolean verbose;

    public String getApplicationImage() {
        return applicationImage;
    }

    public String getInstanceImage() {
        return instanceImage;
    }

    public String getApplication() {
        return application;
    }

    public int getInstances() {
        return instances;
    }

    public String getConfigScript() {
        return configScript;
    }

    public int getCpu() {
        return cpu;
    }

    public int getMemory() {
        return memory;
    }

    public int getMaxInstances() {
        return maxInstances;
    }

    public int getCpuTarget() {
        return cpuTarget;
    }

    public boolean isVerbose() {
        return verbose;
    }

    @Override
    public String toString(){
        return "SampleDomainSpec{" +
                "applicationImage='" +applicationImage+
                ", instanceImage=" +instanceImage+
                ", application=" + application+
                ", instances=" + instances+
                ", configScript=" +configScript+
                ", cpu=" +cpu+
                ", memory=" +memory+
                ", maxInstances=" +maxInstances+
                ", cpuTarget=" +maxInstances+
                ", verbose=" +verbose+
                "}";
    }


}
